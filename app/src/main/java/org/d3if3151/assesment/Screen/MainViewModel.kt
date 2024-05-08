package org.d3if3151.assesment.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3151.assesment.database.AbsenDao
import org.d3if3151.assesment.model.Absen

class MainViewModel(dao: AbsenDao) : ViewModel() {
//    val data = mutableListOf<Absen>()
//
//    fun addAbsen(nama: String, nim: String, status: String){
//        val newAbsen = Absen(id = 1, nama = nama, NIM = nim, status = status)
//        data.add(0, newAbsen)
//    }
//
//    fun removeAbsen(absen: Absen) {
//        data.remove(absen)
//    }
    val data: StateFlow<List<Absen>> = dao.getAbsen().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
)
}