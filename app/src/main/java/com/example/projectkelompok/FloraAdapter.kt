package com.example.projectkelompok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.projectkelompok.databinding.ItemFloraBinding

// custom adapter: mengubah satu FloraModel menjadi satu tampilan item_flora.xml
class FloraAdapter(context: Context, private val items: List<FloraModel>) :
    ArrayAdapter<FloraModel>(context, 0, items) {

    // dipanggil ListView untuk setiap baris yang akan tampil di layar
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. bikin tampilan satu kartu dari item_flora.xml
        val binding = ItemFloraBinding.inflate(LayoutInflater.from(context), parent, false)

        // 2. ambil data flora untuk baris ke-position
        val data = items[position]

        // 3. tempel isi data ke view di kartu, foto dimuat pakai Glide
        binding.textNama.text = data.nama
        binding.textNamaLatin.text = data.namaLatin
        binding.textLokasi.text = data.lokasi
        binding.textTanggal.text = data.tanggal
        binding.textKategori.text = data.kategori

        Glide.with(context).load(data.fotoResId).into(binding.imgFlora)

        // 4. kembalikan kartu yang sudah terisi ke ListView
        return binding.root
    }
}
