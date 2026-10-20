package com.example.projectkelompok

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentBerandaBinding

// tab Beranda: isi lama MainActivity (sapaan, tombol Detail & Log Out) + ringkasan + tips
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
        binding.namaSaya.text = "Halo, $tampungNama"

        // kartu ringkasan: jumlah flora diambil dari DataFlora
        binding.textJumlahFlora.text = "${DataFlora.listFlora.size} flora tercatat"

        binding.btnDetail.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            // logout() ada di MainActivity, jadi dipanggil lewat requireActivity()
            (requireActivity() as MainActivity).logout()
        }

        // daftar tips: SimpleAdapter + simple_list_item_2 (judul + keterangan)
        val listTips = listOf(
            mapOf("title" to "Siram di pagi hari", "desc" to "Air tidak cepat menguap, akar sempat menyerap."),
            mapOf("title" to "Cek tanah sebelum menyiram", "desc" to "Siram hanya kalau tanah 2 cm teratas terasa kering."),
            mapOf("title" to "Putar pot seminggu sekali", "desc" to "Supaya tanaman tumbuh tegak, tidak miring ke arah cahaya."),
            mapOf("title" to "Bersihkan debu di daun", "desc" to "Lap pakai kain lembap agar daun tetap bisa berfotosintesis."),
            mapOf("title" to "Pakai pupuk organik", "desc" to "Kompos atau air cucian beras, cukup sebulan sekali.")
        )
        val adapter = SimpleAdapter(
            requireContext(),
            listTips,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listTips.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // lepas binding supaya view lama tidak "nyangkut" di memori
        _binding = null
    }
}
