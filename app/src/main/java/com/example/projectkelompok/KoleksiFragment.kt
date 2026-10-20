package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentKoleksiBinding

// tab Koleksi: daftar flora pakai ListView + custom adapter (FloraAdapter)
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

        // data flora diambil dari DataFlora, lalu ditempel ke ListView lewat adapter
        val listFlora = DataFlora.listFlora
        val adapter = FloraAdapter(requireContext(), listFlora)
        binding.listFlora.adapter = adapter

        // dipanggil waktu salah satu kartu flora diklik
        binding.listFlora.setOnItemClickListener { _, _, position, _ ->
            val flora = listFlora[position]

            // kirim data flora ke DetailFloraActivity lewat Intent extra
            val intent = Intent(requireContext(), DetailFloraActivity::class.java)
            intent.putExtra("nama", flora.nama)
            intent.putExtra("namaLatin", flora.namaLatin)
            intent.putExtra("lokasi", flora.lokasi)
            intent.putExtra("tanggal", flora.tanggal)
            intent.putExtra("kategori", flora.kategori)
            intent.putExtra("fotoResId", flora.fotoResId)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
