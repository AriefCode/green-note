package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentKoleksiBinding

// tab Koleksi: daftar flora pakai ListView + custom adapter (FloraAdapter), bisa difilter per kategori
class KoleksiFragment : Fragment() {
    private var _binding: FragmentKoleksiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKoleksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listFlora = DataFlora.listFlora

        // listTampil = isi ListView saat ini, berubah setiap chip kategori diganti
        var listTampil = listFlora
        binding.listFlora.adapter = FloraAdapter(requireContext(), listTampil)

        // angka di judul dan chip dihitung dari data, bukan ditulis manual
        binding.textJumlah.text = "${listFlora.size} Spesimen"
        binding.chipSemua.text = "Semua (${listFlora.size})"
        binding.chipHias.text = "Tanaman Hias (${listFlora.filter { it.kategori == "Tanaman Hias" }.size})"
        binding.chipBunga.text = "Bunga (${listFlora.filter { it.kategori == "Bunga" }.size})"
        binding.chipObat.text = "Tanaman Obat (${listFlora.filter { it.kategori == "Tanaman Obat" }.size})"
        binding.chipPohon.text = "Pohon (${listFlora.filter { it.kategori == "Pohon" }.size})"

        // dipanggil setiap kali chip yang aktif berganti
        binding.chipGroupKategori.setOnCheckedStateChangeListener { _, checkedIds ->
            // 1. ambil id chip yang aktif, lalu saring data sesuai kategorinya
            listTampil = when (checkedIds.firstOrNull()) {
                R.id.chipHias -> listFlora.filter { it.kategori == "Tanaman Hias" }
                R.id.chipBunga -> listFlora.filter { it.kategori == "Bunga" }
                R.id.chipObat -> listFlora.filter { it.kategori == "Tanaman Obat" }
                R.id.chipPohon -> listFlora.filter { it.kategori == "Pohon" }
                else -> listFlora
            }
            // 2. pasang adapter baru dengan data hasil saringan
            binding.listFlora.adapter = FloraAdapter(requireContext(), listTampil)
        }

        // dipanggil waktu salah satu kartu flora diklik
        binding.listFlora.setOnItemClickListener { _, _, position, _ ->
            // pakai listTampil, karena position mengikuti urutan baris yang sedang tampil
            val flora = listTampil[position]

            // kirim data flora ke DetailFloraActivity lewat Intent extra
            val intent = Intent(requireContext(), DetailFloraActivity::class.java)
            intent.putExtra("nama", flora.nama)
            intent.putExtra("namaLatin", flora.namaLatin)
            intent.putExtra("lokasi", flora.lokasi)
            intent.putExtra("tanggal", flora.tanggal)
            intent.putExtra("kategori", flora.kategori)
            intent.putExtra("fotoResId", flora.fotoResId)
            intent.putExtra("kondisi", flora.kondisi)
            intent.putExtra("catatan", flora.catatan)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
