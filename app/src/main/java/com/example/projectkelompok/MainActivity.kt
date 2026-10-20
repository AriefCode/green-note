package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projectkelompok.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// MainActivity sekarang jadi "rumah" bottom navigation.
// Isi halaman (Beranda, Koleksi, Catat, Profil) ada di Fragment masing-masing.
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // padding bawah = 0, inset bawah diurus sendiri oleh BottomNavigationView
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // pasang Toolbar sebagai action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Beranda"
            subtitle = "Catatan flora di sekitarmu"
        }

        // fragment awal hanya dipasang sekali (bukan setiap layar diputar)
        if (savedInstanceState == null) {
            replaceFragment(BerandaFragment(), "Beranda")
        }

        // dipanggil setiap kali salah satu tab bottom nav diklik
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_beranda -> {
                    replaceFragment(BerandaFragment(), "Beranda")
                    true
                }
                R.id.nav_koleksi -> {
                    replaceFragment(KoleksiFragment(), "Koleksi")
                    true
                }
                R.id.nav_catat -> {
                    replaceFragment(CatatFragment(), "Catat")
                    true
                }
                R.id.nav_profil -> {
                    replaceFragment(ProfilFragment(), "Profil")
                    true
                }
                else -> false
            }
        }
    }

    // ganti isi fragmentContainer dengan fragment baru + ganti judul Toolbar
    private fun replaceFragment(fragment: Fragment, judul: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
        supportActionBar?.title = judul
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

    // satu fungsi logout, dipakai menu Toolbar, BerandaFragment, dan ProfilFragment
    // (tidak private supaya bisa dipanggil dari fragment)
    fun logout() {
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
