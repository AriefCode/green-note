# Pertemuan 7: Bottom Navigation + ListView (persiapan UTS)

Bagian ini MENGGANTIKAN "Tugas yang sedang dikerjakan" di CLAUDE.md.
Tugas Pertemuan 6 (toolbar, splash, login, isLogin, tema) SUDAH SELESAI. Jangan dirombak kecuali perlu dipindah.
Tidak ada video. Tujuan utamanya: Arief PAHAM projeknya karena minggu depan UTS.

## Tugas sekarang
1. Bottom navigation, menu sesuai fitur & desain Green Note
2. ListView: simple list item DAN custom adapter, data sesuai tema (flora)
3. Lengkapi komponen yang sudah dipelajari di praktikum (lihat daftar di bawah)

## Materi baru yang BOLEH (Pertemuan 7, dari repo praktikum)
- `BottomNavigationView` + `res/menu/bottom_nav_menu.xml` + `FrameLayout` sebagai container fragment
- Ganti fragment dengan pola `replaceFragment()`:
  ```kotlin
  private fun replaceFragment(fragment: Fragment) {
      supportFragmentManager.beginTransaction()
          .replace(binding.fragmentContainer.id, fragment)
          .commit()
  }
  binding.bottomNavView.setOnItemSelectedListener {
      when (it.itemId) {
          R.id.nav_beranda -> { replaceFragment(BerandaFragment()); true }
          else -> false
      }
  }
  ```
  JANGAN pakai `addToBackStack` untuk perpindahan tab (kalau dipakai, tombol back bikin tab aktif tidak sinkron).
  Fragment awal dipasang hanya kalau `savedInstanceState == null`.
- Fragment dengan ViewBinding:
  ```kotlin
  private var _binding: FragmentXxxBinding? = null
  private val binding get() = _binding!!
  // onCreateView: _binding = FragmentXxxBinding.inflate(inflater, container, false); return binding.root
  // onDestroyView: _binding = null   <- wajib, praktikum lupa ini
  ```
  Di fragment pakai `requireContext()` / `requireActivity()` (bukan `this`).
- ListView + `ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)` + `setOnItemClickListener`
- `SimpleAdapter` + `android.R.layout.simple_list_item_2` dengan `listOf(mapOf("title" to ..., "desc" to ...))`
- Custom adapter: `class XxxAdapter(context, private val items: List<Model>) : ArrayAdapter<Model>(context, 0, items)`,
  override `getView`, inflate pakai `ItemXxxBinding`. Model pakai `data class`.
- Glide (`com.github.bumptech.glide:glide:4.16.0`) BOLEH ditambahkan, ini materi praktikum.
- `MaterialCardView`, `CoordinatorLayout` + `AppBarLayout`
- WebView (Pertemuan 5) + permission INTERNET
- Intent dengan extra: `putExtra(...)` / `getStringExtra(...)` / `getIntExtra(...)`

## Tetap JANGAN
- RecyclerView (BELUM materi; desain Koleksi di Figma kelihatan seperti RecyclerView, tetap pakai ListView)
- ViewPager, TabLayout, Navigation Component, BottomAppBar dengan FAB "nempel" di tengah
- Database/Room/Firebase. Data flora = list hardcode di Kotlin.
- Semua larangan lain di CLAUDE.md tetap berlaku.

## Rancangan fitur (boleh disesuaikan setelah baca Figma)
MainActivity jadi "rumah" bottom nav (pola BaseActivity di praktikum). Splash & Login tetap mengarah ke MainActivity.
- Toolbar: SATU toolbar di MainActivity di atas FrameLayout, judul diganti per tab
  (`supportActionBar?.title = ...`). Menu Logout yang sudah ada tetap jalan.
- Edge-to-edge: padding bawah root = 0, biarkan BottomNavigationView yang mengurus inset bawah (seperti praktikum).

| Tab | Fragment | Isi | Komponen |
|---|---|---|---|
| Beranda | BerandaFragment | Sapaan "Halo, <username>" dari SharedPreferences, kartu ringkasan, daftar "Tips Perawatan" | MaterialCardView, SimpleAdapter + simple_list_item_2 |
| Koleksi | KoleksiFragment | Daftar flora (foto, nama, nama latin, lokasi, tanggal), klik -> DetailFloraActivity | Custom adapter `FloraAdapter` + `item_flora.xml` + `FloraModel`, Glide |
| Catat | CatatFragment | Form catat flora (nama, lokasi, deskripsi) + tombol Simpan -> validasi kosong -> AlertDialog konfirmasi -> Snackbar "Tersimpan (demo)" | TextInputLayout, AlertDialog, Snackbar |
| Profil | ProfilFragment | Username, daftar menu: Tentang Green Note, Bantuan, Logout | ArrayAdapter + simple_list_item_1, Toast, AlertDialog |

- DetailFloraActivity: toolbar + tombol back, data dari Intent extra, foto pakai Glide,
  tombol "Baca di Wikipedia" -> WebViewActivity (URL Wikipedia nama latin).
- Foto flora: drawable lokal di `res/drawable/flora_*.png`, dimuat Glide pakai resource id
  (`Glide.with(context).load(data.fotoResId).into(...)`), jadi tetap jalan tanpa internet.
- Ikon bottom nav: vector drawable sederhana di `res/drawable/ic_nav_*.xml`.
- Warna, font, logo ikut bagian Desain di CLAUDE.md. Tidak ada hex hardcode di layout.

## Mode belajar (UTS)
- Sisakan maksimal 3 `TODO(human)`, SATU PER SATU, di bagian yang paling mungkin ditanya dosen:
  1. isi `when` di listener bottom nav (minimal satu cabang)
  2. isi `getView` di FloraAdapter (menempelkan data ke view)
  3. isi `setOnItemClickListener` di KoleksiFragment (Intent + putExtra ke DetailFloraActivity)
- Setiap file baru diberi komentar singkat Bahasa Indonesia di bagian penting.
