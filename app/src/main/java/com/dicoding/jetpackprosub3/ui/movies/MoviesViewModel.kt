package com.dicoding.jetpackprosub3.ui.movies

import androidx.lifecycle.ViewModel
import com.dicoding.jetpackprosub3.data.source.DataRepository

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getMovies(sort: String) = dataRepository.getMovies(sort)
}