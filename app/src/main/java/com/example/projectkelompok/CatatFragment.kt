package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.projectkelompok.data.AppDatabase
import com.example.projectkelompok.data.entity.CatatanFloraEntity
import com.example.projectkelompok.databinding.FragmentCatatBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

// tab Catat: form catat flora, disimpan ke Room (tabel catatan_flora)
class CatatFragment : Fragment() {
    private var _binding: FragmentCatatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // kamera dan galeri belum dibuat, tombolnya cuma kasih tahu user
        binding.btnAmbilFoto.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur kamera menyusul", Toast.LENGTH_SHORT).show()
        }
        binding.btnPilihGaleri.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur kamera menyusul", Toast.LENGTH_SHORT).show()
        }

        binding.btnSimpan.setOnClickListener {
            // 1. ambil teks dari form, trim() membuang spasi di depan/belakang
            val nama = binding.edtNama.text.toString().trim()
            val namaLatin = binding.edtNamaLatin.text.toString().trim()
            val lokasi = binding.edtLokasi.text.toString().trim()
            val tanggal = binding.edtTanggal.text.toString().trim()
            val jurnal = binding.edtJurnal.text.toString().trim()
            // kategori = teks chip yang sedang dipilih (selalu ada, karena selectionRequired)
            val chipId = binding.chipGroupKategori.checkedChipId
            val kategori = binding.root.findViewById<Chip>(chipId).text.toString()
            val jadwalSiram = binding.switchJadwal.isChecked

            // 2. cek kosong (isi spasi doang juga dianggap kosong)
            if (nama.isBlank() || namaLatin.isBlank() || lokasi.isBlank() || tanggal.isBlank() || jurnal.isBlank()) {
                Toast.makeText(requireContext(), "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // 3. minta konfirmasi dulu sebelum menyimpan
                AlertDialog.Builder(requireContext())
                    .setTitle("Simpan Catatan")
                    .setMessage("Simpan catatan \"$nama\" di $lokasi?")
                    .setPositiveButton("Simpan") { dialog, _ ->
                        dialog.dismiss()
                        // matikan tombol dulu supaya tidak tersimpan dobel kalau diklik berkali-kali
                        binding.btnSimpan.isEnabled = false
                        val db = AppDatabase.getInstance(requireContext())

                        // 4. simpan ke Room di dalam coroutine (insert adalah fungsi suspend)
                        viewLifecycleOwner.lifecycleScope.launch {
                            try {
                                db.catatanFloraDao().insert(
                                    CatatanFloraEntity(
                                        namaPopuler = nama,
                                        namaLatin = namaLatin,
                                        kategori = kategori,
                                        lokasi = lokasi,
                                        tanggal = tanggal,
                                        jurnal = jurnal,
                                        jadwalSiram = jadwalSiram
                                    )
                                )

                                // 5. berhasil: kosongkan form + kasih tahu lewat Snackbar
                                binding.edtNama.text?.clear()
                                binding.edtNamaLatin.text?.clear()
                                binding.edtLokasi.text?.clear()
                                binding.edtTanggal.text?.clear()
                                binding.edtJurnal.text?.clear()
                                binding.chipGroupKategori.check(R.id.chipDaunHias)
                                binding.switchJadwal.isChecked = false
                                binding.btnSimpan.isEnabled = true
                                Snackbar.make(binding.root, "Tersimpan ke Jurnal Botani", Snackbar.LENGTH_LONG)
                                    .setAction("Lihat") {
                                        startActivity(Intent(requireContext(), JurnalBotaniActivity::class.java))
                                    }
                                    .show()
                            } catch (e: Exception) {
                                // gagal: aktifkan lagi tombolnya supaya user bisa coba lagi
                                binding.btnSimpan.isEnabled = true
                                Toast.makeText(requireContext(), "Gagal menyimpan catatan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        binding.btnDraf.setOnClickListener {
            Toast.makeText(requireContext(), "Draf disimpan (demo)", Toast.LENGTH_SHORT).show()
        }

        // buka daftar catatan yang tersimpan di Room
        binding.btnLihatJurnal.setOnClickListener {
            startActivity(Intent(requireContext(), JurnalBotaniActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
