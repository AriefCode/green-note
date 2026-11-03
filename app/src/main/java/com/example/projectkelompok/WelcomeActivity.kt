package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityWelcomeBinding

// halaman pertama untuk user yang belum login, isinya perkenalan + dua pintu masuk
class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // user baru -> Register, finish() supaya setelah login tombol back tidak balik ke sini
        binding.btnMulai.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, RegisterActivity::class.java))
            finish()
        }

        // sudah punya akun -> Login
        binding.layoutKeLogin.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            finish()
        }
    }
}
