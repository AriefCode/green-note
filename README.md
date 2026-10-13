# Green Note

Aplikasi Android bertema lingkungan untuk mencatat flora di sekitar (nama, foto, deskripsi singkat).
Project ini adalah tugas praktikum Pemrograman Mobile.

> “Catat setiap hijau di sekitarmu.”

## Fitur

- **Splash screen** — menampilkan logo, nama aplikasi, dan tagline selama 2 detik.
- **Login** — pengecekan username dan password terhadap akun demo.
- **Ingat status login** — status disimpan di SharedPreferences, jadi saat aplikasi dibuka lagi langsung masuk ke halaman utama.
- **Toolbar** — judul, subjudul, dan menu Logout di halaman utama; tombol back di halaman detail.
- **Logout** — dialog konfirmasi, lalu data login dihapus dan kembali ke halaman login.

## Alur aplikasi

```
SplashScreenActivity (LAUNCHER) → tunggu 2 detik → cek isLogin
   ├─ true  → MainActivity
   └─ false → LoginActivity → login sukses → MainActivity

MainActivity
   ├─ Detail → DetailActivity
   └─ Logout → hapus SharedPreferences → LoginActivity
```

## Akun demo

Tidak ada database atau API. Akun di-hardcode di `LoginActivity.kt`.

| Username | Password |
|----------|----------|
| `admin`  | `admin`  |

Username dan password peka huruf besar/kecil.

## Teknologi

- Kotlin + layout XML (View system), `AppCompatActivity`
- ViewBinding
- Material Components (Material 3), ConstraintLayout, CardView
- SharedPreferences (nama file `user_pref`)
- Coroutine `lifecycleScope` untuk jeda splash screen

| Konfigurasi | Nilai |
|-------------|-------|
| minSdk | 24 (Android 7.0) |
| targetSdk / compileSdk | 37 |
| Android Gradle Plugin | 9.3.3 |
| Gradle | 9.5.0 (lewat wrapper) |

## Yang perlu disiapkan

- **Git**
- **Android Studio** versi terbaru yang mendukung Android Gradle Plugin 9.3
- **Android SDK Platform 37** (dipasang lewat SDK Manager di Android Studio)
- **Emulator** atau HP Android minimal Android 7.0 (API 24)
- Koneksi internet saat pertama kali membuka project, karena Gradle akan mengunduh dependency

## Cara menjalankan

### 1. Ambil project dari GitHub

Kalau **belum punya** project-nya di komputer, clone dulu:

```bash
git clone https://github.com/AriefCode/green-note.git
cd green-note
```

Kalau **sudah pernah clone** dan hanya ingin mengambil perubahan terbaru:

```bash
cd green-note
git pull origin master
```

### 2. Buka di Android Studio

1. Buka Android Studio, pilih **File → Open**.
2. Pilih folder `green-note` (folder yang berisi `settings.gradle.kts`), lalu klik **OK**.
3. Kalau muncul pertanyaan *Trust project?*, pilih **Trust Project**.

### 3. Tunggu Gradle Sync

Android Studio otomatis menjalankan Gradle Sync dan mengunduh dependency. Tunggu sampai
progress bar di kanan bawah selesai.

- File `local.properties` (lokasi Android SDK) dibuat otomatis oleh Android Studio. File ini
  sengaja tidak ikut di repo.
- Kalau diminta memasang SDK Platform 37 atau JDK, klik tautan **Install** yang muncul.
- Kalau sync tidak jalan sendiri: **File → Sync Project with Gradle Files**.

### 4. Siapkan perangkat

Pilih salah satu:

- **Emulator** — buka **Device Manager**, buat perangkat baru (API 24 ke atas), lalu jalankan.
- **HP Android** — aktifkan **Opsi Developer** dan **USB Debugging**, lalu sambungkan ke komputer lewat kabel USB.

### 5. Jalankan aplikasi

Pilih perangkat di toolbar atas, lalu klik tombol **Run ▶** (atau `Shift + F10`).

### 6. Coba aplikasinya

1. Splash screen muncul 2 detik, lalu pindah ke halaman Login.
2. Masukkan akun demo di atas, lalu tekan **Login**. Kalau salah, muncul dialog "Login Gagal".
3. Di halaman utama, buka menu titik tiga di Toolbar untuk **Logout**, atau tekan **Detail** untuk membuka halaman detail.
4. Tutup aplikasi lalu buka lagi tanpa logout: setelah splash, aplikasi langsung masuk ke halaman utama.

## Build lewat terminal (opsional)

Linux / macOS:

```bash
./gradlew assembleDebug
```

Windows (CMD / PowerShell):

```bat
.\gradlew.bat assembleDebug
```

APK hasil build ada di `app/build/outputs/apk/debug/app-debug.apk`.

Catatan: kalau project belum pernah dibuka di Android Studio, buat dulu file `local.properties`
di root project berisi lokasi Android SDK, contoh: `sdk.dir=/home/nama/Android/Sdk`.

## Struktur project

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/projectkelompok/
│   ├── SplashScreenActivity.kt   # splash + routing isLogin
│   ├── LoginActivity.kt          # pengecekan username & password
│   ├── MainActivity.kt           # halaman utama, Toolbar, logout
│   └── DetailActivity.kt         # halaman detail, Toolbar + tombol back
└── res/
    ├── layout/                   # activity_splash_screen, activity_login, activity_main, activity_detail
    ├── menu/main_menu.xml        # menu Logout di Toolbar
    ├── font/                     # Domine, Plus Jakarta Sans
    ├── drawable/                 # logo_green_note
    └── values/                   # colors.xml, themes.xml, strings.xml
```

## Desain

Warna dan font mengikuti grand design Figma.

| Nama | Hex | Dipakai untuk |
|------|-----|---------------|
| `green_note_primary` | `#9F4211` | Toolbar, tombol, tagline |
| `green_note_secondary` | `#381742` | Aksen |
| `green_note_background` | `#FFF8F1` | Latar halaman |
| `green_note_sand` | `#FAEDD1` | Aksen krem |
| `green_note_text_primary` | `#20012B` | Judul dan teks utama |
| `green_note_text_secondary` | `#4C444D` | Teks keterangan |

Font: **Domine** (judul dan tagline) dan **Plus Jakarta Sans** (teks biasa dan tombol).

## Masalah yang sering muncul

| Masalah | Solusi |
|---------|--------|
| Gradle Sync gagal saat pertama kali | Pastikan internet tersambung, lalu **File → Sync Project with Gradle Files**. |
| `SDK location not found` | Buka project lewat Android Studio, atau buat `local.properties` berisi `sdk.dir=...`. |
| `Failed to find target with hash string 'android-37'` | Pasang **Android SDK Platform 37** lewat **Tools → SDK Manager**. |
| HP tidak terdeteksi | Cek **USB Debugging** sudah aktif dan izinkan komputer di pop-up yang muncul di HP. |
| Ingin mengulang dari halaman Login | Tekan **Logout**, atau hapus data aplikasi di pengaturan HP. |
