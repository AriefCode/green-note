package com.example.projectkelompok

// model satu data flora (dipakai FloraAdapter & DetailFloraActivity)
data class FloraModel(
    val nama: String,
    val namaLatin: String,
    val lokasi: String,
    val tanggal: String,
    val kategori: String,
    val fotoResId: Int,
    val kondisi: String,
    val catatan: String
)
