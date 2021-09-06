package com.dicoding.jetpackprosub3.ui.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
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
class FavoriteTvViewModelTest {

    private lateinit var viewModel: FavoriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvViewModel(dataRepository)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(dataRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShows().value
        verify(dataRepository).getFavoriteTvShows()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getFavTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavTvShows() {
        viewModel.setFavTvShow(DataDummy.getDetailTvShow())
        verify(dataRepository).setFavoriteTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(dataRepository)
    }
}