package com.dicoding.jetpackprosub3.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest {

    private lateinit var viewModel: FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(dataRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(dataRepository.getFavoriteMovies()).thenReturn(movies)
        val movie = viewModel.getFavMovies().value
        verify(dataRepository).getFavoriteMovies()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavMovie(DataDummy.getDetailMovie())
        verify(dataRepository).setFavoriteMovie(DataDummy.getDetailMovie(), true)
        verifyNoMoreInteractions(dataRepository)
    }
}