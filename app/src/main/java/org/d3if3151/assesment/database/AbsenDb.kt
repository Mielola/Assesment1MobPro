package org.d3if3151.assesment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3151.assesment.model.Absen

@Database(entities = [Absen::class], version = 1, exportSchema = false)
abstract class AbsenDb : RoomDatabase() {
    abstract val dao: AbsenDao

    companion object {
        @Volatile
        private var INSTANCE: AbsenDb? = null

        fun getInstance(context: Context): AbsenDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AbsenDb::class.java,
                        "absen2.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}