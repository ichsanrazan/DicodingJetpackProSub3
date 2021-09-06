package com.dicoding.jetpackprosub3

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.jetpackprosub3.utils.DataDummy
import com.dicoding.jetpackprosub3.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyMovie.size
                )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.circleimage_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_name)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyTvShow.size
                )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.circleimage_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_name)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavMovies() {
        onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyMovie.size
                )
        )
    }

    @Test
    fun loadDetailFavMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.fab_add_fav)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_movies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.circleimage_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_fav)).perform(ViewActions.click())
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_name)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavTvShows() {
        onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyTvShow.size
                )
        )
    }

    @Test
    fun loadDetailFavTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.fab_add_fav)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())
        onView(ViewMatchers.withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_favorite_tv_show)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.circleimage_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_fav)).perform(ViewActions.click())
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_name)).check(matches(isDisplayed()))
    }
}