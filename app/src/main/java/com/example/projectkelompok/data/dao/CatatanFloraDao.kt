package com.example.projectkelompok.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projectkelompok.data.entity.CatatanFloraEntity

// DAO (Data Access Object) = daftar perintah ke tabel catatan_flora
// suspend: dijalankan di dalam coroutine supaya tidak bikin UI macet
@Dao
interface CatatanFloraDao {

    // Read: ambil semua catatan, yang terbaru paling atas
    @Query("SELECT * FROM catatan_flora ORDER BY createdAt DESC")
    suspend fun getAll(): List<CatatanFloraEntity>

    // Create: tambah satu catatan baru
    @Insert
    suspend fun insert(catatan: CatatanFloraEntity)

    // Delete: hapus catatan (dicari berdasarkan id / primary key)
    @Delete
    suspend fun delete(catatan: CatatanFloraEntity)
}
