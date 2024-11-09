package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameDetailDto(
    val name: String? = null,
    val background_image: String? = null,
    val description_raw: String? = null,
    val metacritic: Int? = null,
    val website: String? = null
)
