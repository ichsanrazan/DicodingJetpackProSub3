package com.dicoding.jetpackprosub3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.data.source.local.room.FilmDao
import com.dicoding.jetpackprosub3.utils.SortUtils
import com.dicoding.jetpackprosub3.utils.SortUtils.MOVIES_ENTITIES
import com.dicoding.jetpackprosub3.utils.SortUtils.TV_ENTITIES

class LocalDataSource(private val filmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MoviesEntity> = filmDao.getMovies(
        SortUtils.getSortedQuery(sort, MOVIES_ENTITIES))

    fun getMovieById(id: Int): LiveData<MoviesEntity> = filmDao.getMovieById(id)

    fun getFavMovies(): DataSource.Factory<Int, MoviesEntity> = filmDao.getFavMovies()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvEntity> = filmDao.getTvShows(
        SortUtils.getSortedQuery(sort, TV_ENTITIES))

    fun getTvShowById(id: Int): LiveData<TvEntity> = filmDao.getTvShowById(id)

    fun getFavTvShows(): DataSource.Factory<Int, TvEntity> = filmDao.getFavTvShows()

    fun insertMovies(movies: List<MoviesEntity>) = filmDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MoviesEntity, newState: Boolean) {
        movie.isFav = newState
        filmDao.updateMovie(movie)
    }

    fun updateMovie(movie: MoviesEntity, newState: Boolean) {
        movie.isFav = newState
        filmDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvEntity>) = filmDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.isFav = newState
        filmDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.isFav = newState
        filmDao.updateTvShow(tvShow)
    }
}