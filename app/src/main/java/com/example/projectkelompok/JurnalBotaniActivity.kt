package com.example.projectkelompok

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectkelompok.data.AppDatabase
import com.example.projectkelompok.data.entity.CatatanFloraEntity
import com.example.projectkelompok.databinding.ActivityJurnalBotaniBinding
import kotlinx.coroutines.launch

// Jurnal Botani: menampilkan (Read) dan menghapus (Delete) isi tabel catatan_flora
class JurnalBotaniActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJurnalBotaniBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: CatatanFloraAdapter
    // list ini yang dipegang adapter; isinya diganti tiap kali ambil data dari Room
    private val catatanList = mutableListOf<CatatanFloraEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityJurnalBotaniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Jurnal Botani"
            setDisplayHomeAsUpEnabled(true)
        }

        // ambil objek database (Singleton)
        db = AppDatabase.getInstance(this)

        // adapter + lambda hapus
        adapter = CatatanFloraAdapter(catatanList) { catatan -> hapusCatatan(catatan) }
        binding.rvCatatan.layoutManager = LinearLayoutManager(this)
        binding.rvCatatan.adapter = adapter
    }

    // onResume: dipanggil tiap kali halaman ini tampil lagi, jadi data selalu terbaru
    override fun onResume() {
        super.onResume()
        ambilCatatan()
    }

    // Read: ambil semua catatan dari Room lalu tampilkan
    private fun ambilCatatan() {
        lifecycleScope.launch {
            val hasil = db.catatanFloraDao().getAll()
            catatanList.clear()
            catatanList.addAll(hasil)
            adapter.notifyDataSetChanged()

            // subtitle = jumlah catatan, teks kosong tampil kalau list kosong
            supportActionBar?.subtitle = "${catatanList.size} CATATAN"
            if (catatanList.isEmpty()) {
                binding.tvKosong.visibility = View.VISIBLE
            } else {
                binding.tvKosong.visibility = View.GONE
            }
        }
    }

    // Delete: hapus satu catatan, lalu ambil ulang datanya
    private fun hapusCatatan(catatan: CatatanFloraEntity) {
        lifecycleScope.launch {
            db.catatanFloraDao().delete(catatan)
            ambilCatatan()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
