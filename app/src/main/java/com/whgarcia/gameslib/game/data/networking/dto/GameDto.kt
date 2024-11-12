package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id: Int,
    val name: String? = null,
    val released: String? = null,
    val background_image: String? = null,
    val parent_platforms: List<ParentPlatform>,
    val genres: List<Genre>
)

@Serializable
data class ParentPlatform(
    val platform: Platform
)

@Serializable
data class Platform(
    val id: Int,
    val slug: String
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)