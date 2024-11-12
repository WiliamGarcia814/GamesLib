package com.whgarcia.gameslib.game.domain

data class Game(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val parent_platforms: List<ParentPlatformDomain>,
    val genres: List<GenreDomain>
)

data class ParentPlatformDomain(
    val platform: PlatformDomain
)

data class PlatformDomain(
    val id: Int,
    val slug: String
)

data class GenreDomain(
    val id: Int,
    val name: String
)