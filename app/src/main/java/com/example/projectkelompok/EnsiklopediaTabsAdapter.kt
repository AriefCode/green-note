package com.example.projectkelompok

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// adapter ViewPager2 untuk 3 tab Ensiklopedia (pola EleventhTabsAdapter)
class EnsiklopediaTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3
    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FloraLokalFragment()
            1 -> TanamanObatFragment()
            2 -> PanduanFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
