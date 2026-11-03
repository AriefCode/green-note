package com.example.projectkelompok

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.projectkelompok.databinding.FragmentBerandaBinding
import com.example.projectkelompok.databinding.ItemFloraBinding

// tab Beranda: sapaan, statistik, ajakan mencatat, temuan terakhir, dan kartu tips
class BerandaFragment : Fragment() {
    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ambil username yang disimpan waktu login
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val tampungNama = sharedPref.getString("username", "")
        binding.namaSaya.text = "Halo, $tampungNama!"

        // statistik dihitung dari DataFlora, bukan ditulis manual
        val listFlora = DataFlora.listFlora
        binding.textTotalKoleksi.text = "${listFlora.size}"
        binding.textTotalBunga.text = "${listFlora.filter { it.kategori == "Bunga" }.size}"

        // pindah tab lewat fungsi bukaTab() di MainActivity
        binding.btnMulaiCatat.setOnClickListener {
            (requireActivity() as MainActivity).bukaTab(R.id.nav_catat)
        }
        binding.textLihatSemua.setOnClickListener {
            (requireActivity() as MainActivity).bukaTab(R.id.nav_koleksi)
        }

        // kartu Tips membuka halaman TipsActivity
        binding.cardTips.setOnClickListener {
            startActivity(Intent(requireContext(), TipsActivity::class.java))
        }

        // temuan terakhir: 3 flora pertama, kartunya dibuat satu per satu lewat loop
        for (flora in listFlora.take(3)) {
            // 1. bikin satu kartu dari item_flora.xml, belum dipasang (false)
            val itemBinding = ItemFloraBinding.inflate(layoutInflater, binding.layoutTemuan, false)

            // 2. tempel data flora ke kartu
            itemBinding.textNama.text = flora.nama
            itemBinding.textNamaLatin.text = flora.namaLatin
            itemBinding.textLokasi.text = flora.lokasi
            itemBinding.textTanggal.text = flora.tanggal
            itemBinding.textKategori.text = flora.kategori
            itemBinding.textKondisi.text = flora.kondisi
            Glide.with(this).load(flora.fotoResId).into(itemBinding.imgFlora)

            // 3. kartu diklik -> buka detail flora ini
            itemBinding.root.setOnClickListener {
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

            // 4. pasang kartu ke wadah di layout
            binding.layoutTemuan.addView(itemBinding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // lepas binding supaya view lama tidak "nyangkut" di memori
        _binding = null
    }
}
