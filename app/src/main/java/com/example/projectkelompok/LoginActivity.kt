package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
            // ime() = tinggi keyboard, supaya kolom tidak ketutup
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // akun demo, di-hardcode karena tidak pakai database / API
        val akunUsername = "rania"
        val akunPassword = "greennote123"

        binding.btnSubmit.setOnClickListener {
            // 1. ambil teks yang diketik user
            val user = binding.formUsr.text.toString()
            val pass = binding.formPw.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                // 2. ada kolom kosong -> kasih tahu user, tidak perlu cek akun
                Toast.makeText(this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

                var loginBerhasil = false
                // 3. cek apakah username & password cocok dengan akun demo
                if (akunUsername == user && akunPassword == pass){
                    loginBerhasil = true
                }

                // 3b. cek juga akun hasil Register yang disimpan di SharedPreferences
                val regUsername = sharedPref.getString("reg_username", "")
                val regPassword = sharedPref.getString("reg_password", "")
                if (regUsername == user && regPassword == pass) {
                    loginBerhasil = true
                }
                if (loginBerhasil) {
                    // 4. simpan status login + username ke SharedPreferences
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", true)
                    editor.putString("username", user)
                    editor.apply()

                    // 5. pindah ke MainActivity, finish() supaya tombol back tidak balik ke Login
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

        // belum punya akun -> ke Register, finish() supaya Login tidak menumpuk
        binding.textKeRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}
