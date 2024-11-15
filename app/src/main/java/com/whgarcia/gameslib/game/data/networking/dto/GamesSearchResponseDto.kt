package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesSearchResponseDto(
    val count: Int,
    val results: List<GameSearchDto>
)
