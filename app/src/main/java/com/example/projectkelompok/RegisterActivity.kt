package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityRegisterBinding

// halaman daftar akun: akun disimpan di SharedPreferences (belum pakai database)
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // ime() = tinggi keyboard, supaya kolom paling bawah tidak ketutup
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        binding.btnDaftar.setOnClickListener {
            // 1. ambil teks dari semua kolom
            val nama = binding.edtNama.text.toString()
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val konfirmasi = binding.edtKonfirmasi.text.toString()

            if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || konfirmasi.isEmpty()) {
                // 2. ada kolom kosong
                Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (password != konfirmasi) {
                // 3. password dan konfirmasinya tidak sama
                Toast.makeText(this, "Konfirmasi password tidak sama", Toast.LENGTH_SHORT).show()
            } else {
                // 4. simpan akun ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("reg_username", username)
                editor.putString("reg_password", password)
                editor.apply()

                // 5. pindah ke Login, finish() supaya halaman ini tidak tersisa di belakang
                Toast.makeText(this, "Akun berhasil dibuat, silakan masuk", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }

        // sudah punya akun -> langsung ke Login
        binding.textKeLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}
