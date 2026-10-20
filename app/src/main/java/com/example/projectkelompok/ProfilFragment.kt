package com.example.projectkelompok

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.FragmentProfilBinding

// tab Profil: username + daftar menu sederhana (ArrayAdapter + simple_list_item_1)
class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // tampilkan username dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        binding.textUsername.text = sharedPref.getString("username", "")

        val listMenu = listOf("Tentang Green Note", "Bantuan", "Logout")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listMenu)
        binding.listMenuProfil.adapter = adapter

        // aksi tiap menu ditentukan dari posisi item yang diklik
        binding.listMenuProfil.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Tentang Green Note")
                        .setMessage("Green Note: aplikasi untuk mencatat flora di sekitarmu.\n“Catat setiap hijau di sekitarmu.”")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
                1 -> {
                    Toast.makeText(requireContext(), "Pilih tab Catat untuk menambah flora baru", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    (requireActivity() as MainActivity).logout()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
