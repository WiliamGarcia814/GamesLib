package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesSearchResponseDto(
    val count: Int,
    val results: List<GameSearchDto>
)

@Serializable
data class GameSearchDto(
    val id: Int,
    val name: String? = null,
    val background_image: String? = null
)