package com.dicoding.jetpackprosub3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity

@Database(
    entities = [MoviesEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, FilmDatabase::class.java, "Film.db").build()
            }
    }
}