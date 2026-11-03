package com.example.projectkelompok

import android.os.Bundle
import android.view.MenuItem
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityTipsBinding

// daftar tips perawatan, dibuka dari kartu Tips di Beranda dan menu Profil
class TipsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tips Perawatan"
            subtitle = "JURNAL MUSIM INI"
            setDisplayHomeAsUpEnabled(true)
        }

        // daftar tips: SimpleAdapter + simple_list_item_2 (judul + keterangan)
        val listTips = listOf(
            mapOf("title" to "Siram di pagi hari", "desc" to "Air tidak cepat menguap, akar sempat menyerap."),
            mapOf("title" to "Cek tanah sebelum menyiram", "desc" to "Siram hanya kalau tanah 2 cm teratas terasa kering."),
            mapOf("title" to "Putar pot seminggu sekali", "desc" to "Supaya tanaman tumbuh tegak, tidak miring ke arah cahaya."),
            mapOf("title" to "Bersihkan debu di daun", "desc" to "Lap pakai kain lembap agar daun tetap bisa berfotosintesis."),
            mapOf("title" to "Pakai pupuk organik", "desc" to "Kompos atau air cucian beras, cukup sebulan sekali.")
        )
        val adapter = SimpleAdapter(
            this,
            listTips,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listTips.adapter = adapter
    }

    // tombol back di Toolbar punya id bawaan android.R.id.home
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
