package com.whgarcia.gameslib.game.data.mappers

import com.whgarcia.gameslib.game.data.networking.dto.GameDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GameDto
import com.whgarcia.gameslib.game.data.networking.dto.Genre
import com.whgarcia.gameslib.game.data.networking.dto.ParentPlatform
import com.whgarcia.gameslib.game.data.networking.dto.Platform
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GameDetail
import com.whgarcia.gameslib.game.domain.GenreDomain
import com.whgarcia.gameslib.game.domain.ParentPlatformDomain
import com.whgarcia.gameslib.game.domain.PlatformDomain

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name ?: "",
        released = released ?: "",
        background_image = background_image ?: "",
        parent_platforms = parent_platforms.map { it.toParentPlatform() },
        genres = genres.map { it.toGenre() }
    )
}

fun ParentPlatform.toParentPlatform(): ParentPlatformDomain {
    return ParentPlatformDomain(
        platform = platform.toPlatform()
    )
}

fun Platform.toPlatform(): PlatformDomain {
    return PlatformDomain(
        id = id,
        slug = slug
    )
}

fun Genre.toGenre(): GenreDomain{
    return GenreDomain(
        id = id,
        name = name
    )
}

fun GameDetailDto.toGameDetail(): GameDetail {
    return GameDetail(
        name = name ?: "",
        background_image = background_image ?: "",
        description_raw = description_raw ?: "",
        metacritic = metacritic ?: 0,
        website = website ?: ""
    )
}