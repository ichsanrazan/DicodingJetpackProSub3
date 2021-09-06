package com.dicoding.jetpackprosub3.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpackprosub3.data.source.DataRepository
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(dataRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(dataRepository.getTvShows("BEST")).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows("BEST").value?.data
        verify(dataRepository).getTvShows("BEST")
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShows("BEST").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}