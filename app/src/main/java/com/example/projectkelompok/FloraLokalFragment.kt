package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectkelompok.databinding.FragmentFloraLokalBinding

// tab Flora Lokal: RecyclerView grid 2 kolom (pola TabCFragment)
class FloraLokalFragment : Fragment() {
    private var _binding: FragmentFloraLokalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFloraLokalBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 10 flora khas Indonesia, gambar dari Wikimedia Commons
    private val floraList = listOf(
        FloraInfoModel("Anggrek Bulan", "Phalaenopsis amabilis", "https://thumb.wikimedia.org/wikipedia/commons/thumb/5/59/Phalaenopsis_amabilis_Orchi_198.jpg/500px-Phalaenopsis_amabilis_Orchi_198.jpg"),
        FloraInfoModel("Melati", "Jasminum sambac", "https://thumb.wikimedia.org/wikipedia/commons/thumb/f/f3/Arabian_jasmin%2C_Tunisia_2010.jpg/500px-Arabian_jasmin%2C_Tunisia_2010.jpg"),
        FloraInfoModel("Padma Raksasa", "Rafflesia arnoldii", "https://thumb.wikimedia.org/wikipedia/commons/thumb/d/dd/Rafflesia_arnoldii%2C_Sumatra.jpg/500px-Rafflesia_arnoldii%2C_Sumatra.jpg"),
        FloraInfoModel("Bunga Bangkai", "Amorphophallus titanum", "https://thumb.wikimedia.org/wikipedia/commons/thumb/5/5c/Amorphophallus_titanum_%28corpse_flower%29_-_2.jpg/500px-Amorphophallus_titanum_%28corpse_flower%29_-_2.jpg"),
        FloraInfoModel("Kantong Semar", "Nepenthes ampullaria", "https://thumb.wikimedia.org/wikipedia/commons/thumb/0/00/Nep_amp_295.jpg/500px-Nep_amp_295.jpg"),
        FloraInfoModel("Kenanga", "Cananga odorata", "https://thumb.wikimedia.org/wikipedia/commons/thumb/5/5b/Cananga_odorata_01.JPG/500px-Cananga_odorata_01.JPG"),
        FloraInfoModel("Cempaka", "Magnolia champaca", "https://thumb.wikimedia.org/wikipedia/commons/thumb/2/20/Magnolia_champaca_flower.jpg/500px-Magnolia_champaca_flower.jpg"),
        FloraInfoModel("Kamboja", "Plumeria rubra", "https://thumb.wikimedia.org/wikipedia/commons/thumb/a/aa/Champa_tree_with_pink_flowers_in_Islamabad%2C_Pakistan.jpg/500px-Champa_tree_with_pink_flowers_in_Islamabad%2C_Pakistan.jpg"),
        FloraInfoModel("Matoa", "Pometia pinnata", "https://thumb.wikimedia.org/wikipedia/commons/thumb/3/3a/Pometia_pinnata%2C_flowering.jpg/500px-Pometia_pinnata%2C_flowering.jpg"),
        FloraInfoModel("Ulin", "Eusideroxylon zwageri", "https://thumb.wikimedia.org/wikipedia/commons/thumb/5/52/Eusideroxylon_zwageri.JPG/500px-Eusideroxylon_zwageri.JPG")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // lambda: apa yang terjadi saat kartu diklik -> buka Wikipedia di WebViewActivity
        val floraAdapter = FloraInfoAdapter(floraList) { selectedItem ->
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            // spasi di nama latin diganti "_" sesuai format alamat Wikipedia
            intent.putExtra("url", "https://id.wikipedia.org/wiki/" + selectedItem.namaLatin.replace(" ", "_"))
            intent.putExtra("judul", selectedItem.nama)
            startActivity(intent)
        }
        binding.rvFloraLokal.apply {
            // Mode Grid 2 kolom
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = floraAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
