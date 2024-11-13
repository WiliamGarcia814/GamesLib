package com.whgarcia.gameslib.game.data.networking.dto

import kotlinx.serialization.Serializable

// Clase de datos para representar los detalles de un juego
@Serializable
data class GameDetailDto(
    val name: String? = null,
    val background_image: String? = null,
    val description_raw: String? = null,
    val metacritic: Int? = null,
    val released: String? = null,
    val website: String? = null,
    val rating_top: Int? = null,
    val ratings: List<RatingDetailDto>,
    val platforms: List<PlatformsDetailDto>,
    val developers: List<DeveloperDetailDto>,
    val genres: List<GenreDetailDto>,
    val tags: List<TagDetailDto>,
    val publishers: List<PublisherDetailDto>
)

@Serializable
data class RatingDetailDto(
    val id: Int,
    val title: String,
    val count: Int
)

@Serializable
data class PlatformDetailDto(
    val id: Int,
    val name: String
)

@Serializable
data class PlatformsDetailDto(
    val platform: PlatformDetailDto
)

@Serializable
data class DeveloperDetailDto(
    val id: Int,
    val name: String
)

@Serializable
data class GenreDetailDto(
    val id: Int,
    val name: String
)

@Serializable
data class TagDetailDto(
    val id: Int,
    val name: String
)

@Serializable
data class PublisherDetailDto(
    val id: Int,
    val name: String
)