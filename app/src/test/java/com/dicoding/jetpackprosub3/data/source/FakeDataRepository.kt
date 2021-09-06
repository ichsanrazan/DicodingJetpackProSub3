package com.dicoding.jetpackprosub3.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.jetpackprosub3.data.source.local.LocalDataSource
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.data.source.remote.ApiResponse
import com.dicoding.jetpackprosub3.data.source.remote.RemoteDataSource
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.Movie
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.MovieDetailResponse
import com.dicoding.jetpackprosub3.data.source.remote.response.tv.TvShow
import com.dicoding.jetpackprosub3.data.source.remote.response.tv.TvShowDetailResponse
import com.dicoding.jetpackprosub3.utils.AppExecutors
import com.dicoding.jetpackprosub3.vo.Resource

class FakeDataRepository constructor(
        private val rmtDataSource: RemoteDataSource,
        private val lclDataSource: LocalDataSource,
        private val appExec: AppExecutors
) : DataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<Movie>>(appExec) {
            override fun loadFromDb(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(lclDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                    rmtDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movie = MoviesEntity(
                            id = response.id,
                            backdropPath = response.backdropPath,
                            genres = "",
                            overview = response.overview,
                            posterPath = response.posterPath,
                            releaseDate = response.releaseDate,
                            runtime = 0,
                            title = response.title,
                            voteAverage = response.voteAverage,
                            isFav = false
                    )
                    movieList.add(movie)
                }
                lclDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MovieDetailResponse>(appExec) {
            override fun loadFromDb(): LiveData<MoviesEntity> = lclDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MoviesEntity?): Boolean =
                    data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                    rmtDataSource.getDetailMovie(movieId.toString())

            override fun saveCallResult(data: MovieDetailResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val movie = MoviesEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.toString(),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.releaseDate,
                        runtime = data.runtime,
                        title = data.title,
                        voteAverage = data.voteAverage,
                        isFav = false
                )
                lclDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(lclDataSource.getFavMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MoviesEntity, state: Boolean) {
        lclDataSource.setFavoriteMovie(movie, state)
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvShow>>(appExec) {
            override fun loadFromDb(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()

                return LivePagedListBuilder(lclDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                    rmtDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val movieList = ArrayList<TvEntity>()
                for (response in data) {
                    val movie = TvEntity(
                            id = response.id,
                            backdropPath = response.backdropPath,
                            genres = "",
                            overview = response.overview,
                            posterPath = response.posterPath,
                            releaseDate = response.firstAirDate,
                            runtime = 0,
                            name = response.name,
                            voteAverage = response.voteAverage,
                            isFav = false
                    )
                    movieList.add(movie)
                }
                lclDataSource.insertTvShows(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvShowDetailResponse>(appExec) {
            override fun loadFromDb(): LiveData<TvEntity> = lclDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvEntity?): Boolean =
                    data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                    rmtDataSource.getDetailTvShow(tvShowId.toString())

            override fun saveCallResult(data: TvShowDetailResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val tvShow = TvEntity(
                        id = data.id,
                        backdropPath = data.backdropPath,
                        genres = genres.toString(),
                        overview = data.overview,
                        posterPath = data.posterPath,
                        releaseDate = data.firstAirDate,
                        runtime = data.episodeRunTime.first(),
                        name = data.name,
                        voteAverage = data.voteAverage,
                        isFav = false
                )
                lclDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(lclDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean) {
        lclDataSource.setFavoriteTvShow(tvShow, state)
    }
}