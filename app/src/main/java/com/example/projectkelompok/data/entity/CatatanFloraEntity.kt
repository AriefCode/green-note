package com.example.projectkelompok.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity = cetakan satu baris di tabel "catatan_flora"
// tiap properti di data class ini jadi satu kolom
@Entity(tableName = "catatan_flora")
data class CatatanFloraEntity(
    // id diisi otomatis oleh Room (1, 2, 3, ...), jadi waktu insert cukup pakai 0
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaPopuler: String,
    val namaLatin: String,
    val kategori: String,
    val lokasi: String,
    val tanggal: String,
    val jurnal: String,
    val jadwalSiram: Boolean,
    // waktu dibuat (milidetik), dipakai untuk mengurutkan catatan terbaru di atas
    val createdAt: Long = System.currentTimeMillis()
)
