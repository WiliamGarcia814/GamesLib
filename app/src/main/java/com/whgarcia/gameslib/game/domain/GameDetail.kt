package com.whgarcia.gameslib.game.domain

data class GameDetail(
    val name: String,
    val background_image: String,
    val description_raw: String,
    val metacritic: Int,
    val released: String,
    val website: String,
    val rating_top: Int,
    val ratings: List<RatingDetailDomain>,
    val platforms: List<PlatformsDetailDomain>,
    val developers: List<DeveloperDetailDomain>,
    val genres: List<GenreDetailDomain>,
    val tags: List<TagDetailDomain>,
    val publishers: List<PublisherDetailDomain>
)

data class RatingDetailDomain(
    val id: Int,
    val title: String,
    val count: Int
)

data class PlatformDetailDomain(
    val id: Int,
    val name: String
)

data class PlatformsDetailDomain(
    val platform: PlatformDetailDomain
)

data class DeveloperDetailDomain(
    val id: Int,
    val name: String
)

data class GenreDetailDomain(
    val id: Int,
    val name: String
)

data class TagDetailDomain(
    val id: Int,
    val name: String
)

data class PublisherDetailDomain(
    val id: Int,
    val name: String
)