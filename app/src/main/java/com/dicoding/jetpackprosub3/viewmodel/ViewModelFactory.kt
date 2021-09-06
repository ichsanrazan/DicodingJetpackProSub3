package com.dicoding.jetpackprosub3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.di.Injection
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel
import com.dicoding.jetpackprosub3.ui.favorite.movies.FavoriteMoviesViewModel
import com.dicoding.jetpackprosub3.ui.favorite.tv.FavoriteTvViewModel
import com.dicoding.jetpackprosub3.ui.movies.MoviesViewModel
import com.dicoding.jetpackprosub3.ui.tv.TvViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                FavoriteMoviesViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvViewModel::class.java) -> {
                FavoriteTvViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}