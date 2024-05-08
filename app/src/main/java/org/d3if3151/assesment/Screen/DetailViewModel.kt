package org.d3if3151.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3151.assesment.database.AbsenDao
import org.d3if3151.assesment.model.Absen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: AbsenDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    fun insert(nama: String, NIM: String, status: String, time: String){
        val absen = Absen(
            nama = nama,
            NIM = NIM,
            status = status,
            time = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(absen)
        }
    }

    suspend fun getAbsen(id: Long): Absen? {
        return dao.getAbsenById(id)
    }

    fun update(id: Long, nama: String, NIM: String, status: String, time: String){
        val absen = Absen(
            id = id,
            nama = nama,
            NIM = NIM,
            status = status,
            time = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(absen)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}