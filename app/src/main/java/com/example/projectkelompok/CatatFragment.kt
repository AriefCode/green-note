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

        binding.btnSimpan.setOnClickListener {
            // 1. ambil teks dari form
            val nama = binding.edtNama.text.toString()
            val lokasi = binding.edtLokasi.text.toString()
            val deskripsi = binding.edtDeskripsi.text.toString()

            // 2. cek kosong
            if (nama.isEmpty() || lokasi.isEmpty() || deskripsi.isEmpty()) {
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
                        binding.edtLokasi.text?.clear()
                        binding.edtDeskripsi.text?.clear()
                        Snackbar.make(binding.root, "Tersimpan (demo)", Snackbar.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
