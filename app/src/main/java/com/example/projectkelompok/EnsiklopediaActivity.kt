package com.example.projectkelompok

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityEnsiklopediaBinding
import com.google.android.material.tabs.TabLayoutMediator

// Ensiklopedia Flora: TabLayout + ViewPager2, dibuka dari tombol di Beranda (pola EleventhActivity)
class EnsiklopediaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnsiklopediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEnsiklopediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Ensiklopedia Flora"
            subtitle = "JELAJAH FLORA NUSANTARA"
            setDisplayHomeAsUpEnabled(true)
        }

        // 1. Inisialisasi adapter
        val tabsAdapter = EnsiklopediaTabsAdapter(this)
        // 2. Pasang adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter
        // 3. Hubungkan TabLayout dan ViewPager2: atur teks, ikon, dan badge tiap tab
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Flora Lokal"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_leaf)
                    // Badge dengan angka = jumlah flora di tab ini
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 10
                    badge.backgroundColor = ContextCompat.getColor(this, R.color.green_note_primary)
                }
                1 -> {
                    tab.text = "Tanaman Obat"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_pot)
                    // Badge dengan angka = jumlah tanaman obat
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 8
                    badge.backgroundColor = ContextCompat.getColor(this, R.color.green_note_primary)
                }
                2 -> {
                    tab.text = "Panduan"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_book)
                    // Badge tanpa angka (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.backgroundColor = ContextCompat.getColor(this, R.color.green_note_primary)
                }
            }
        }.attach()
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
