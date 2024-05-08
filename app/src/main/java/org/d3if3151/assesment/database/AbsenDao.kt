package org.d3if3151.assesment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3151.assesment.model.Absen

@Dao
interface AbsenDao {
    @Insert
    suspend fun insert(absen: Absen)

    @Update
    suspend fun update(absen: Absen)

    @Query("SELECT * FROM absen ORDER BY time DESC")
    fun getAbsen(): Flow<List<Absen>>

    @Query("SELECT * FROM absen WHERE id = :id")
    suspend fun getAbsenById(id: Long): Absen

    @Query("DELETE FROM absen WHERE id = :id")
    suspend fun deleteById(id: Long)
}