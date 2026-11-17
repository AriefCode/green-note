package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkelompok.databinding.FragmentTanamanObatBinding

// tab Tanaman Obat: RecyclerView memanjang ke bawah, adapter SAMA dengan Flora Lokal
class TanamanObatFragment : Fragment() {
    private var _binding: FragmentTanamanObatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTanamanObatBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 8 tanaman obat, gambar dari Wikimedia Commons
    private val obatList = listOf(
        FloraInfoModel("Jahe", "Zingiber officinale", "https://thumb.wikimedia.org/wikipedia/commons/thumb/d/db/Zingiber_officinale_fresh_rhizome.JPG/500px-Zingiber_officinale_fresh_rhizome.JPG"),
        FloraInfoModel("Kunyit", "Curcuma longa", "https://thumb.wikimedia.org/wikipedia/commons/thumb/a/ab/Turmeric_inflorescence.jpg/500px-Turmeric_inflorescence.jpg"),
        FloraInfoModel("Temulawak", "Curcuma zanthorrhiza", "https://thumb.wikimedia.org/wikipedia/commons/thumb/c/c9/Temulawak.jpg/500px-Temulawak.jpg"),
        FloraInfoModel("Lidah Buaya", "Aloe vera", "https://thumb.wikimedia.org/wikipedia/commons/thumb/3/3f/A_potted_aloe_vera_plant.jpg/500px-A_potted_aloe_vera_plant.jpg"),
        FloraInfoModel("Serai", "Cymbopogon citratus", "https://thumb.wikimedia.org/wikipedia/commons/thumb/7/7e/Gardenology.org-IMG_2892_rbgs11jan.jpg/500px-Gardenology.org-IMG_2892_rbgs11jan.jpg"),
        FloraInfoModel("Sambiloto", "Andrographis paniculata", "https://thumb.wikimedia.org/wikipedia/commons/thumb/3/39/Andrographis_paniculata_%28Kalpa%29_in_Narshapur_forest%2C_AP_W2_IMG_0867.jpg/500px-Andrographis_paniculata_%28Kalpa%29_in_Narshapur_forest%2C_AP_W2_IMG_0867.jpg"),
        FloraInfoModel("Sirih", "Piper betle", "https://thumb.wikimedia.org/wikipedia/commons/thumb/9/99/Piper_betle_plant.jpg/500px-Piper_betle_plant.jpg"),
        FloraInfoModel("Kumis Kucing", "Orthosiphon aristatus", "https://thumb.wikimedia.org/wikipedia/commons/thumb/8/8a/Orthosiphon_aristatus_in_Kadavoor.jpg/500px-Orthosiphon_aristatus_in_Kadavoor.jpg")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // klik kartu -> buka halaman Wikipedia tanaman itu
        val obatAdapter = FloraInfoAdapter(obatList) { selectedItem ->
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra("url", "https://id.wikipedia.org/wiki/" + selectedItem.namaLatin.replace(" ", "_"))
            intent.putExtra("judul", selectedItem.nama)
            startActivity(intent)
        }
        binding.rvTanamanObat.apply {
            // Mode Linear: satu kartu per baris
            layoutManager = LinearLayoutManager(requireContext())
            adapter = obatAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
