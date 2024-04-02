package org.d3if3151.assesment.Screen

import androidx.lifecycle.ViewModel
import org.d3if3151.assesment.model.Absen

class MainViewModel : ViewModel() {
    val data = mutableListOf<Absen>()

    fun addAbsen(nama: String, nim: String, status: String){
        val newAbsen = Absen(id = 1, nama = nama, NIM = nim, status = status)
        data.add(0, newAbsen)
    }

    fun removeAbsen(absen: Absen) {
        data.remove(absen)
    }
}