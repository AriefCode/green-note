package com.example.projectkelompok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentCatatBinding
import com.google.android.material.snackbar.Snackbar

// tab Catat: form catat flora (demo, belum disimpan ke database)
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
            // 1. ambil teks dari form
            val nama = binding.edtNama.text.toString()
            val namaLatin = binding.edtNamaLatin.text.toString()
            val lokasi = binding.edtLokasi.text.toString()
            val tanggal = binding.edtTanggal.text.toString()
            val jurnal = binding.edtJurnal.text.toString()

            // 2. cek kosong
            if (nama.isEmpty() || namaLatin.isEmpty() || lokasi.isEmpty() || tanggal.isEmpty() || jurnal.isEmpty()) {
                Toast.makeText(requireContext(), "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // 3. minta konfirmasi dulu sebelum "menyimpan"
                AlertDialog.Builder(requireContext())
                    .setTitle("Simpan Catatan")
                    .setMessage("Simpan catatan \"$nama\" di $lokasi?")
                    .setPositiveButton("Simpan") { dialog, _ ->
                        dialog.dismiss()
                        // 4. kosongkan form + kasih tahu lewat Snackbar
                        binding.edtNama.text?.clear()
                        binding.edtNamaLatin.text?.clear()
                        binding.edtLokasi.text?.clear()
                        binding.edtTanggal.text?.clear()
                        binding.edtJurnal.text?.clear()
                        binding.chipGroupKategori.check(R.id.chipDaunHias)
                        binding.switchJadwal.isChecked = false
                        Snackbar.make(binding.root, "Tersimpan (demo)", Snackbar.LENGTH_SHORT).show()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
