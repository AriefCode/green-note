package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkelompok.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // pasang Toolbar sebagai action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Green Note"
            subtitle = "Catatan flora di sekitarmu"
        }

        // ambil username yang disimpan waktu login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val tampungNama = sharedPref.getString("username", "")

        binding.namaSaya.text = "Halo, $tampungNama"

        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.btnDetail.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java )
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    // tampilkan menu (titik tiga) di Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // dipanggil waktu salah satu item menu Toolbar diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // satu fungsi logout, dipakai tombol Log Out dan menu Toolbar
    private fun logout() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin log out?")
            .setPositiveButton("Ya"){ dialog, _ ->
                dialog.dismiss()

                // hapus semua data login di SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                // pindah ke Login, finish() supaya tombol back tidak balik ke sini
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
