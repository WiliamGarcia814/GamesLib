package com.whgarcia.gameslib.game.domain

data class GameDetail(
    val name: String,
    val background_image: String,
    val description_raw: String,
    val metacritic: Int,
    val website: String,
)
