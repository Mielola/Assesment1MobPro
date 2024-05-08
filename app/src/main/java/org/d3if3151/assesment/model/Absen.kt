package org.d3if3151.assesment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "absen")
data class Absen (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val NIM: String,
    val status: String,
    val time: String
)