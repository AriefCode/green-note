package com.example.projectkelompok

// data flora di-hardcode (belum pakai database), isinya dari frame Koleksi, Beranda, dan Detail di Figma
object DataFlora {
    val listFlora = listOf(
        FloraModel(
            "Monstera Deliciosa", "Monstera deliciosa", "Kebun Rumah, Jaksel", "15 Feb 2025", "Tanaman Hias", R.drawable.flora_monstera,
            "Tumbuh Tunas Baru",
            "Daun lebar berlubang khas yang tumbuh subur di sudut teduh kebun. Satu tunas baru mulai menggulung di dekat batang utama."
        ),
        FloraModel(
            "Kembang Sepatu Merah", "Hibiscus rosa-sinensis", "Halaman Belakang", "14 Feb 2025", "Bunga", R.drawable.flora_sepatu,
            "Mekar Penuh",
            "Kelopak merah menyala begitu anggun di samping pagar kayu halaman belakang. Dipotret pada pukul 07.15 WIB tepat setelah embun pagi menyapu kelopaknya. Putik sari kuning menjuntai sempurna dengan 5 helai mahkota mekar simetris. Tampak seekor lebah madu sempat singgah sejenak."
        ),
        FloraModel(
            "Lidah Mertua", "Sansevieria trifasciata", "Ruang Tamu", "12 Feb 2025", "Tanaman Hias", R.drawable.flora_lidah_mertua,
            "Siram 2 Hari Lagi",
            "Daun tegak dengan corak loreng kuning keemasan. Sahabat setia sudut meja baca yang selalu kokoh tanpa rewel air."
        ),
        FloraModel(
            "Ketapang Kencana", "Terminalia mantaly", "Taman Kota, Jaksel", "09 Feb 2025", "Pohon", R.drawable.flora_ketapang_kencana,
            "Kanopi Rindang",
            "Tingkat ranting bertingkat bagai payung alami yang menyejukkan terik siang. Struktur cabangnya sangat puitis dilihat dari bawah."
        ),
        FloraModel(
            "Kembang Kertas Ungu", "Bougainvillea spectabilis", "Kebun Tetangga", "05 Feb 2025", "Bunga", R.drawable.flora_kembang_kertas,
            "Bractea Ungu",
            "Gradasi ungu magenta yang begitu renyah bak kertas kalkir di bawah terik mentari. Mekar rimbun memikat lebah madu."
        ),
        FloraModel(
            "Kumis Kucing", "Orthosiphon aristatus", "Kebun Herbal Nenek", "02 Feb 2025", "Tanaman Obat", R.drawable.flora_kumis_kucing,
            "Infusi Alami",
            "Bunga putih dengan benang sari panjang yang menjuntai seperti kumis kucing. Daunnya biasa dikeringkan untuk bahan seduhan herbal."
        )
    )
}
