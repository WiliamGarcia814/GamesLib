package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.GameDetail
import com.whgarcia.gameslib.game.domain.RatingDetailDomain

data class GameDetailUi(
    val name: String,
    val background_image: String,
    val description_raw: String,
    val metacritic: Int,
    val website: String,
    val released: String,
    val rating_top: Int,
    val ratings: List<RatingUi>,
    val platforms: List<String>,
    val developers: List<String>,
    val genres: List<String>,
    val tags: List<String>,
    val publishers: List<String>
)

data class RatingUi(
    val id: Int,
    val title: String,
    val count: Int
)

fun GameDetail.toGameDetailUi(): GameDetailUi{
    return GameDetailUi(
        name = name,
        background_image = background_image,
        description_raw =  description_raw,
        metacritic = metacritic,
        released = formattedDate(released),
        website = website,
        rating_top = rating_top,
        ratings = ratings.map { it.toRatingUi() },
        platforms = platforms.map { it.platform.name },
        developers = developers.map { it.name },
        genres = genres.map { it.name },
        tags = tags.map { it.name },
        publishers = publishers.map { it.name }
    )
}

fun RatingDetailDomain.toRatingUi(): RatingUi {
    return RatingUi(
        id = id,
        title = title,
        count = count,
    )
}