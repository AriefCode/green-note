package com.example.projectkelompok

// data flora di-hardcode (belum pakai database), isinya dari frame Koleksi di Figma
object DataFlora {
    val listFlora = listOf(
        FloraModel("Monstera Deliciosa", "Monstera deliciosa", "Kebun Rumah, Jaksel", "15 Feb 2025", "Tanaman Hias", R.drawable.flora_monstera),
        FloraModel("Kembang Sepatu Merah", "Hibiscus rosa-sinensis", "Halaman Belakang", "14 Feb 2025", "Bunga", R.drawable.flora_sepatu),
        FloraModel("Lidah Mertua", "Sansevieria trifasciata", "Ruang Tamu", "12 Feb 2025", "Tanaman Hias", R.drawable.flora_lidah_mertua),
        FloraModel("Ketapang Kencana", "Terminalia mantaly", "Taman Kota, Jaksel", "09 Feb 2025", "Pohon", R.drawable.flora_ketapang_kencana),
        FloraModel("Kembang Kertas Ungu", "Bougainvillea spectabilis", "Kebun Tetangga", "05 Feb 2025", "Bunga", R.drawable.flora_kembang_kertas),
        FloraModel("Kumis Kucing", "Orthosiphon aristatus", "Kebun Herbal Nenek", "02 Feb 2025", "Tanaman Obat", R.drawable.flora_kumis_kucing)
    )
}
