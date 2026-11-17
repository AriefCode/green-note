package com.example.projectkelompok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// tab Panduan: cuma teks statis, jadi cukup inflate layout (pola TabAFragment)
class PanduanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_panduan, container, false)
    }
}
