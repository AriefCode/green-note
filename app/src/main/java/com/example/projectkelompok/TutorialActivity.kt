package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityTutorialBinding

// halaman tutorial setelah Splash (untuk user yang belum login): 3 fragment yang bisa digeser
class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup ViewPager dengan adapter: urutan list = urutan halaman
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialViewPager.adapter = adapter

        // hubungkan titik-titik indikator dengan ViewPager supaya ikut bergeser
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

        // "Lewati" -> langsung ke Selamat Datang, finish() supaya back tidak balik ke tutorial
        binding.textLewati.setOnClickListener {
            startActivity(Intent(this@TutorialActivity, WelcomeActivity::class.java))
            finish()
        }
    }
}
