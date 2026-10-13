package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // akun demo, di-hardcode karena tidak pakai database / API
        val akunUsername = "admin"
        val akunPassword = "admin"

        binding.btnSubmit.setOnClickListener {
            // 1. ambil teks yang diketik user
            val user = binding.formUsr.text.toString()
            val pass = binding.formPw.text.toString()

            var loginBerhasil = false
            // 2. cek apakah username & password cocok dengan akun demo
            if (akunUsername == user && akunPassword == pass){
                loginBerhasil = true
            }


            if (loginBerhasil) {
                // 3. simpan status login + username ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", user)
                editor.apply()

                // 4. pindah ke MainActivity, finish() supaya tombol back tidak balik ke Login
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // login gagal -> kasih tahu user lewat dialog
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah.")
                    .setPositiveButton("OK"){ dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
