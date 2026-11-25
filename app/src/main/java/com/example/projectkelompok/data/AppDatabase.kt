package com.example.projectkelompok.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectkelompok.data.dao.CatatanFloraDao
import com.example.projectkelompok.data.entity.CatatanFloraEntity

// RoomDatabase: daftar semua tabel (entities) + versi database
@Database(entities = [CatatanFloraEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // pintu masuk ke DAO, isinya dibuatkan Room lewat KSP
    abstract fun catatanFloraDao(): CatatanFloraDao

    companion object {
        // Singleton: satu aplikasi cukup punya SATU objek database
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // kalau sudah ada, pakai yang lama; kalau belum, buat baru
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "green_note_db" // nama file database di HP
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
