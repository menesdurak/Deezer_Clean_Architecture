package com.menesdurak.appcentcasestudycleanarchitecture.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.menesdurak.appcentcasestudycleanarchitecture.data.local.entity.FavoriteTrack

@Database(entities = [FavoriteTrack::class], version = 1)
abstract class DeezerDatabase : RoomDatabase() {

    abstract fun getDeezerDao(): DeezerDao

    companion object {
        @Volatile
        private var INSTANCE: DeezerDatabase? = null

        fun getDatabase(context: Context): DeezerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeezerDatabase::class.java,
                    "deezer_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}