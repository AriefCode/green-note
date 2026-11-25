package com.example.projectkelompok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkelompok.data.entity.CatatanFloraEntity
import com.example.projectkelompok.databinding.ItemCatatanFloraBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// adapter RecyclerView Jurnal Botani (pola NoteAdapter praktikum)
// onDelete: fungsi dari Activity, dijalankan setelah user menekan "Ya" di dialog hapus
class CatatanFloraAdapter(
    private val catatanList: List<CatatanFloraEntity>,
    private val onDelete: (CatatanFloraEntity) -> Unit
) : RecyclerView.Adapter<CatatanFloraAdapter.CatatanViewHolder>() {

    class CatatanViewHolder(val binding: ItemCatatanFloraBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanViewHolder {
        val binding = ItemCatatanFloraBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CatatanViewHolder(binding)
    }

    // tempel data satu baris tabel ke kartu
    override fun onBindViewHolder(holder: CatatanViewHolder, position: Int) {
        val catatan = catatanList[position]
        holder.binding.tvNamaPopuler.text = catatan.namaPopuler
        holder.binding.tvNamaLatin.text = catatan.namaLatin
        holder.binding.tvKategori.text = catatan.kategori
        holder.binding.tvLokasiTanggal.text = "${catatan.lokasi} • ${catatan.tanggal}"
        holder.binding.tvJurnal.text = catatan.jurnal

        // tombol hapus: minta konfirmasi dulu, baru panggil onDelete
        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus catatan ini?")
                .setPositiveButton("Ya") { _, _ -> onDelete(catatan) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = catatanList.size
}
