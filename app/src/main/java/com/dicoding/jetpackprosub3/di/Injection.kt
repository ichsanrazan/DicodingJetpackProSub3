package com.dicoding.jetpackprosub3.di

import android.content.Context
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.LocalDataSource
import com.dicoding.jetpackprosub3.data.source.local.room.FilmDatabase
import com.dicoding.jetpackprosub3.data.source.remote.RemoteDataSource
import com.dicoding.jetpackprosub3.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = FilmDatabase.getInstance(context)

        val rmtDataSource = RemoteDataSource.getInstance()
        val lclDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExec = AppExecutors()
        return DataRepository.getInstance(rmtDataSource, lclDataSource, appExec)
    }
}