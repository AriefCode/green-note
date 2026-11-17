package com.example.projectkelompok

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkelompok.databinding.ItemFloraInfoBinding

// adapter RecyclerView Ensiklopedia, dipakai bersama oleh tab Flora Lokal & Tanaman Obat
// onItemClick: fungsi dari fragment, dijalankan saat kartu diklik
class FloraInfoAdapter(
    private val floraList: List<FloraInfoModel>,
    private val onItemClick: (FloraInfoModel) -> Unit
) : RecyclerView.Adapter<FloraInfoAdapter.FloraInfoViewHolder>() {

    // ViewHolder menyimpan binding satu kartu supaya view bisa dipakai ulang saat di-scroll
    inner class FloraInfoViewHolder(val binding: ItemFloraInfoBinding) :
        RecyclerView.ViewHolder(binding.root)

    // dipanggil saat RecyclerView butuh kartu baru: inflate item_flora_info.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloraInfoViewHolder {
        val binding = ItemFloraInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FloraInfoViewHolder(binding)
    }

    // dipanggil tiap kali kartu di posisi tertentu perlu diisi data
    override fun onBindViewHolder(holder: FloraInfoViewHolder, position: Int) {
        val item = floraList[position]
        with(holder.binding) {
            // tempel teks ke kartu
            tvNama.text = item.nama
            tvNamaLatin.text = item.namaLatin

            // muat foto dari URL; logo tampil dulu selama foto belum selesai diunduh
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.logo_green_note)
                .into(imgFlora)

            // seluruh kartu bisa diklik -> kirim item ke lambda (buka Wikipedia)
            root.setOnClickListener { onItemClick(item) }
        }
    }

    // jumlah kartu = jumlah data di list
    override fun getItemCount(): Int = floraList.size
}
