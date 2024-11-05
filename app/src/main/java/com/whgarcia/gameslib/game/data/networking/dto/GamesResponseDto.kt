package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    val count: Int,
    val results: List<GameDto>
)
