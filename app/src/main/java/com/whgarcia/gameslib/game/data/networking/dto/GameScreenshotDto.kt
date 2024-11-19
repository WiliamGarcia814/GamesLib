package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameScreenshotResponseDto(
    val count: Int,
    val results: List<GameScreenshotDto>
)

@Serializable
data class GameScreenshotDto(
    val id: Int,
    val image: String,
    val is_deleted: Boolean
)
