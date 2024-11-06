package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.GameDetail

data class GameDetailUi(
    val name: String,
    val background_image: String,
    val description_raw: String,
    val metacritic: Int,
    val website: String
)

fun GameDetail.toGameDetailUi(): GameDetailUi{
    return GameDetailUi(
        name = name,
        background_image = background_image,
        description_raw =  description_raw,
        metacritic = metacritic,
        website = website
    )
}