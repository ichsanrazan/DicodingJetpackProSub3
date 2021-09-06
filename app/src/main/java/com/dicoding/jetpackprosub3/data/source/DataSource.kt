package com.dicoding.jetpackprosub3.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.vo.Resource

interface DataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>>

    fun setFavoriteMovie(movie: MoviesEntity, state: Boolean)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>>

    fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean)
}