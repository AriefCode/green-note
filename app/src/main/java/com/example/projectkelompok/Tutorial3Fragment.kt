package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentTutorial3Binding

// halaman tutorial 3: pakai ViewBinding (pola P7) karena ada tombol "Mulai Sekarang"
class Tutorial3Fragment : Fragment() {
    private var _binding: FragmentTutorial3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tutorial selesai -> ke Selamat Datang, lalu tutup TutorialActivity (bukan cuma fragment-nya)
        binding.btnMulai.setOnClickListener {
            startActivity(Intent(requireContext(), WelcomeActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // lepas binding supaya view lama tidak "nyangkut" di memori
        _binding = null
    }
}
