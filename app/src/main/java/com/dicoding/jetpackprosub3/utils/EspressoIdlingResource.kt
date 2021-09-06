package com.dicoding.jetpackprosub3.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE: String = "GLOBAL"
    private val espIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = espIdlingResource.increment()
    fun decrement() = espIdlingResource.decrement()
    fun getEspressoIdlingResource(): IdlingResource = espIdlingResource
}