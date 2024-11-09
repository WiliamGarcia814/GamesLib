package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameDetailDto(
    val name: String,
    val background_image: String? = null,
    val description_raw: String,
    val metacritic: Int? = null,
    val website: String
)
