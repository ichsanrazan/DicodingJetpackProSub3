package com.dicoding.jetpackprosub3.ui.tv

import androidx.lifecycle.ViewModel
import com.dicoding.jetpackprosub3.data.source.DataRepository

class TvViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getTvShows(sort: String) = dataRepository.getTvShows(sort)
}