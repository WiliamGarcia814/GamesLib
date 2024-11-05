package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id: Int,
    val name: String,
    val background_image: String
)
