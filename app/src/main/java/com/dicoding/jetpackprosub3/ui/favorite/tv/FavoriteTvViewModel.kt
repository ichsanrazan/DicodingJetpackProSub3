package com.dicoding.jetpackprosub3.ui.favorite.tv

import androidx.lifecycle.ViewModel
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity

class FavoriteTvViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getFavTvShows() = dataRepository.getFavoriteTvShows()

    fun setFavTvShow(tvShowEntity: TvEntity) {
        val newState = !tvShowEntity.isFav
        dataRepository.setFavoriteTvShow(tvShowEntity, newState)
    }
}