package com.dicoding.jetpackprosub3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity

@Dao
interface FilmDao {
    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MoviesEntity>

    @RawQuery(observedEntities = [TvEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFav = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvEntity>)

    @Update
    fun updateTvShow(tvShow: TvEntity)
}