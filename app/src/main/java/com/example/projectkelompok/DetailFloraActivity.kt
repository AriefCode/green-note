package com.example.projectkelompok

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.projectkelompok.databinding.ActivityDetailFloraBinding

// halaman detail satu flora, datanya dikirim dari KoleksiFragment lewat Intent extra
// kunci extra: "nama", "namaLatin", "lokasi", "tanggal", "kategori" (String), "fotoResId" (Int)
class DetailFloraActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailFloraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailFloraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. ambil data dari Intent extra
        val nama = intent.getStringExtra("nama")
        val namaLatin = intent.getStringExtra("namaLatin")
        val lokasi = intent.getStringExtra("lokasi")
        val tanggal = intent.getStringExtra("tanggal")
        val kategori = intent.getStringExtra("kategori")
        val fotoResId = intent.getIntExtra("fotoResId", 0)

        // 2. Toolbar + tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = nama
            setDisplayHomeAsUpEnabled(true)
        }

        // 3. tempel data ke view, foto dimuat pakai Glide
        binding.textNama.text = nama
        binding.textNamaLatin.text = namaLatin
        binding.textKategori.text = kategori
        binding.textLokasi.text = "Lokasi: $lokasi"
        binding.textTanggal.text = "Dicatat: $tanggal"
        Glide.with(this).load(fotoResId).into(binding.imgFlora)

        // 4. buka WebView ke halaman Wikipedia nama latin (spasi diganti "_")
        binding.btnWikipedia.setOnClickListener {
            val url = "https://en.m.wikipedia.org/wiki/" + namaLatin?.replace(" ", "_")
            val intent = Intent(this@DetailFloraActivity, WebViewActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("judul", namaLatin)
            startActivity(intent)
        }
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
