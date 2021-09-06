package com.dicoding.jetpackprosub3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.jetpackprosub3.ui.detail.DetailViewModel.Companion.TV
import com.dicoding.jetpackprosub3.utils.DataDummy
import com.dicoding.jetpackprosub3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.getDetailMovie()
    private val dummyMovieId = dummyMovie.id

    private val dummyTvShow = DataDummy.getDetailTvShow()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvEntity>>

    // Get Data Movie Testing
    @Before
    fun setUpMovie() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(dataRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        val data = viewModel.getDetailMovie().value

        verify(dataRepository).getDetailMovie(dummyMovieId)

        //lakukan pada variable lainnya
        assertEquals(dummyDetailMovie.data?.id , data?.data?.id)
        assertEquals(dummyDetailMovie.data?.backdropPath , data?.data?.backdropPath)
        assertEquals(dummyDetailMovie.data?.genres , data?.data?.genres)
        assertEquals(dummyDetailMovie.data?.overview , data?.data?.overview)
        assertEquals(dummyDetailMovie.data?.posterPath , data?.data?.posterPath)
        assertEquals(dummyDetailMovie.data?.releaseDate , data?.data?.releaseDate)
        assertEquals(dummyDetailMovie.data?.runtime , data?.data?.runtime)
        assertEquals(dummyDetailMovie.data?.title , data?.data?.title)
        assertEquals(dummyDetailMovie.data?.voteAverage , data?.data?.voteAverage)
        assertEquals(dummyDetailMovie.data?.isFav , data?.data?.isFav)


        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDetailMovie())
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(dataRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)

        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        viewModel.setFavoriteMovie()
        verify(dataRepository).setFavoriteMovie(movie.value!!.data as MoviesEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    // Get Data TV Show Testing
    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(dataRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV)
        val data = viewModel.getDetailTvShow().value

        verify(dataRepository).getDetailTvShow(dummyTvShowId)

        //lakukan pada variable lainnya
        assertEquals(dummyDetailTvShow.data?.id , data?.data?.id)
        assertEquals(dummyDetailTvShow.data?.backdropPath , data?.data?.backdropPath)
        assertEquals(dummyDetailTvShow.data?.genres , data?.data?.genres)
        assertEquals(dummyDetailTvShow.data?.overview , data?.data?.overview)
        assertEquals(dummyDetailTvShow.data?.posterPath , data?.data?.posterPath)
        assertEquals(dummyDetailTvShow.data?.releaseDate , data?.data?.releaseDate)
        assertEquals(dummyDetailTvShow.data?.runtime , data?.data?.runtime)
        assertEquals(dummyDetailTvShow.data?.name , data?.data?.name)
        assertEquals(dummyDetailTvShow.data?.voteAverage , data?.data?.voteAverage)
        assertEquals(dummyDetailTvShow.data?.isFav , data?.data?.isFav)

        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(dataRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV)
        viewModel.setFavoriteTvShow()
        verify(dataRepository).setFavoriteTvShow(tvShow.value!!.data as TvEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}