package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(
    val count: Int,
    val results: List<GameDto>
)

@Serializable
data class GameDto(
    val id: Int,
    val name: String? = null,
    val released: String? = null,
    val background_image: String? = null,
    val parent_platforms: List<ParentPlatformDto>,
    val genres: List<GenreDto>
)

@Serializable
data class ParentPlatformDto(
    val platform: PlatformDto
)

@Serializable
data class PlatformDto(
    val id: Int,
    val slug: String
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)