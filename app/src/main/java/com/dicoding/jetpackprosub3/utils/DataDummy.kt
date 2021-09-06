package com.dicoding.jetpackprosub3.utils

import com.dicoding.jetpackprosub3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpackprosub3.data.source.local.entity.TvEntity
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.*
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.Genre
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.ProductionCompany
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.ProductionCountry
import com.dicoding.jetpackprosub3.data.source.remote.response.movie.SpokenLanguage
import com.dicoding.jetpackprosub3.data.source.remote.response.tv.*

typealias TvShowGenre = com.dicoding.jetpackprosub3.data.source.remote.response.tv.Genre
typealias TvShowProductionCompany = com.dicoding.jetpackprosub3.data.source.remote.response.tv.ProductionCompany
typealias TvShowProductionCountry = com.dicoding.jetpackprosub3.data.source.remote.response.tv.ProductionCountry
typealias TvShowSpokenLanguage = com.dicoding.jetpackprosub3.data.source.remote.response.tv.SpokenLanguage

object DataDummy {
    fun getMovies(): List<MoviesEntity> {
        return listOf(
            MoviesEntity(
                837007,
                "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                "Comedy, Crime",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "2021-05-26",
                134,
                "Cruella",
                8.7,
                false
            ),
            MoviesEntity(
                691179,
                "/hP7dN2B5ztQgSIN5Qvk63MY4EeO.jpg",
                "Documentary, Comedy, TV Movie, Drama",
                "The cast of Friends reunites for a once-in-a-lifetime celebration of the hit series, an unforgettable evening filled with iconic memories, uncontrollable laughter, happy tears, and special guests.",
                "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg",
                "2021-05-27",
                104,
                "Friends: The Reunion",
                8.5,
                false
            ),
            MoviesEntity(
                615457,
                "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "Crime, Action, Thriller, Comedy",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26",
                92,
                "Nobody",
                8.4,
                false
            )

        )
    }

    fun getDetailMovie(): MoviesEntity {
        return MoviesEntity(
            337404,
            "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            "Comedy, Crime",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "2021-05-26",
            134,
            "Cruella",
            8.7,
            false
        )
    }

    fun getTvShows(): List<TvEntity> {
        return listOf(
            TvEntity(95557,
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "Sci-Fi & Fantasy, Animation, Action & Adventure, Drama",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "2021-03-26",
                45,
                "Invincible",
                9.3,
                false
            ),
            TvEntity(105971,
                "/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                "Sci-Fi & Fantasy, Action & Adventure, Animation",
                "As they traverse a decommisioned medical facility, the Batch encounter an unexpected threat.",
                "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "2021-05-04",
                75,
                "The Bad Batch",
                8.7,
                false
            ),
            TvEntity(1416,
                "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                "Mystery, Drama, Crime",
                "While preparing for the school's Parent-Teacher night, Archie is caught off guard when his former Army General shows up in Riverdale with some unexpected news. Cheryl pushes back after hearing that Hiram and Reggie have expressed interest in taking over the Blossom maple groves. Jughead's unconventional way to get through a case of writer's block causes Tabitha to be concerned for his safety. Finally, Betty and Alice receive some unexpected visitors.",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "2017-01-26",
                45,
                "Riverdale",
                8.6,
                false
            )
        )
    }

    fun getDetailTvShow(): TvEntity {
        return TvEntity(95557,
            "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            "Sci-Fi & Fantasy, Animation, Action & Adventure, Drama",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "2021-03-26",
            45,
            "Invincible",
            9.3,
            false
        )
    }

    // for remote
    fun getRemoteMovies(): List<Movie> {
        return listOf(
            Movie(
                adult = false,
                backdropPath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                genreIds = listOf(35, 80),
                id = 337404,
                originalLanguage = "en",
                originalTitle = "Cruella",
                overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                popularity = 5507.395,
                posterPath = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                releaseDate = "2021-05-26",
                title = "Cruella",
                video = false,
                voteAverage = 8.7,
                voteCount = 2431
            ),
            Movie(
                adult = false,
                backdropPath = "/hP7dN2B5ztQgSIN5Qvk63MY4EeO.jpg",
                genreIds = listOf(99, 35, 10770, 18),
                id = 691179,
                originalLanguage = "en",
                originalTitle = "Friends: The Reunion",
                overview = "The cast of Friends reunites for a once-in-a-lifetime celebration of the hit series, an unforgettable evening filled with iconic memories, uncontrollable laughter, happy tears, and special guests.",
                popularity = 479.324,
                posterPath = "/bT3c4TSOP8vBmMoXZRDPTII6eDa.jpg",
                releaseDate = "2021-05-27",
                title = "Friends: The Reunion",
                video = false,
                voteAverage = 8.5,
                voteCount = 368
            ),
            Movie(
                adult = false,
                backdropPath = "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                genreIds = listOf(80, 28, 53, 35),
                id = 615457,
                originalLanguage = "en",
                originalTitle = "Nobody",
                overview = "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                popularity = 961.52,
                posterPath = "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                releaseDate = "2021-03-26",
                title = "Nobody",
                video = false,
                voteAverage = 8.4,
                voteCount = 1979
            )
        )
    }

    fun getRemoteDetailMovie(): MovieDetailResponse {
        return MovieDetailResponse(
            adult = false,
            backdropPath = "/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            belongsToCollection = BelongsToCollection(
                id = 837007,
                name = "Cruella Collection",
                posterPath = "null",
                backdropPath = "null"
            ),
            budget = 200000000,
            genres = listOf(
                Genre(
                    id = 35,
                    name = "Comedy"
                ),
                Genre(
                    id = 80,
                    name = "Crime"
                )
            ),
            homepage = "https://movies.disney.com/cruella",
            id = 337404,
            imdbId = "tt3228774",
            originalLanguage = "en",
            originalTitle = "Cruella",
            overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            popularity = 5507.395,
            posterPath = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            productionCompanies = listOf(
                ProductionCompany(
                    id = 2,
                    logoPath = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
                    name = "Walt Disney Pictures",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                ProductionCountry(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            releaseDate = "2021-05-26",
            revenue = 88197497,
            runtime = 134,
            spokenLanguages = listOf(
                SpokenLanguage(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Released",
            tagline = "Hello Cruel World",
            title = "Cruella",
            video = false,
            voteAverage = 8.7,
            voteCount = 2431

        )
    }

    fun getRemoteTvShows(): List<TvShow> {
        return listOf(
            TvShow(
                backdropPath = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                firstAirDate = "2021-03-26",
                genreIds = listOf(10765, 16, 10759, 18),
                id = 95557,
                name = "Invincible",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Invincible",
                overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                popularity = 521.067,
                posterPath = "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                voteAverage = 8.9,
                voteCount = 2050
            ),
            TvShow(
                backdropPath = "/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                firstAirDate = "2021-05-04",
                genreIds = listOf(10765, 10759, 16),
                id = 105971,
                name = "The Bad Batch",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "The Bad Batch",
                overview = "The 'Bad Batch' of elite and experimental clones make their way through an ever-changing galaxy in the immediate aftermath of the Clone Wars.",
                popularity = 540.216,
                posterPath = "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                voteAverage = 8.7,
                voteCount = 277
            ),
            TvShow(
                backdropPath = "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                firstAirDate = "2017-01-26",
                genreIds = listOf(9648, 18, 80),
                id = 69050,
                name = "Riverdale",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Riverdale",
                overview = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                popularity = 585.12,
                posterPath = "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                voteAverage = 8.6,
                voteCount = 11409
            )
        )
    }

    fun getRemoteDetailTvShow(): TvShowDetailResponse {
        return TvShowDetailResponse(
            backdropPath = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            createdBy = listOf(
                CreatedBy(
                    id = 68844,
                    creditId = "5ab47d649251417b020149ed",
                    name = "Hayden Schlossberg",
                    gender = 2,
                    profilePath = null
                )
            ),
            episodeRunTime = listOf(45),
            firstAirDate = "2021-03-26",
            genres = listOf(
                TvShowGenre(
                    id = 10765,
                    name = "Sci-Fi & Fantasy"
                ),
                TvShowGenre(
                    id = 16,
                    name = "Animation"
                ),
                    TvShowGenre(
                            id = 10759,
                            name = "Action & Adventure"
                    ),
                    TvShowGenre(
                            id = 18,
                            name = "Drama"
                    )
            ),
            homepage = "https://www.amazon.com/dp/B08WJP55PR",
            id = 95557,
            inProduction = true,
            languages = listOf("en"),
            lastAirDate = "2021-04-30",
            lastEpisodeToAir = LastEpisodeToAir(
                airDate = "2021-04-30",
                episodeNumber = 8,
                id = 2832752,
                name = "WHERE I REALLY COME FROM",
                overview = "Mark must prove he's become the hero he's always wanted to be by stopping an unstoppable force.",
                productionCode = "INVI 108",
                seasonNumber = 1,
                stillPath = "/ijDV8Z23iR5B2ftx0WggiXbfqGi.jpg",
                voteAverage = 9.3,
                voteCount = 3
            ),
            name = "Invincible",
            nextEpisodeToAir = null,
            networks = listOf(
                Network(
                    name = "Amazon",
                    id = 1024,
                    logoPath = "/ifhbNuuVnlwYy5oXA5VIb2YR8AZ.png",
                    originCountry = ""
                )
            ),
            numberOfEpisodes = 8,
            numberOfSeasons = 1,
            originCountry = listOf("US"),
            originalLanguage = "en",
            originalName = "Invincible",
            overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            popularity = 521.067,
            posterPath = "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            productionCompanies = listOf(
                TvShowProductionCompany(
                    id = 20580,
                    logoPath = "/tkFE81jJIqiFYPP8Tho57MXRQEx.png",
                    name = "Amazon Studios",
                    originCountry = "US"
                ),
                TvShowProductionCompany(
                    id = 151645,
                    logoPath = null,
                    name = "Skybound North",
                    originCountry = "US"
                ),
                TvShowProductionCompany(
                    id = 50032,
                    logoPath = null,
                    name = "Skybound Entertainment",
                    originCountry = "US"
                ),
            ),
            productionCountries = listOf(
                TvShowProductionCountry(
                    iso31661 = "CA",
                    name = "Canada"
                ),
                    TvShowProductionCountry(
                            iso31661 = "US",
                            name = "United States of America"
                    )
            ),
            seasons = listOf(
                Season(
                    airDate = "2021-03-26",
                    episodeCount = 8,
                    id = 136020,
                    name = "Season 1",
                    overview = "",
                    posterPath = "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                    seasonNumber = 1
                )
            ),
            spokenLanguages = listOf(
                TvShowSpokenLanguage(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Returning Series",
            tagline = "",
            type = "Scripted",
            voteAverage = 8.9,
            voteCount = 2050
        )
    }
}