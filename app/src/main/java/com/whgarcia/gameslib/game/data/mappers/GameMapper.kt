package com.whgarcia.gameslib.game.data.mappers

import com.whgarcia.gameslib.game.data.networking.dto.DeveloperDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GameDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GameDto
import com.whgarcia.gameslib.game.data.networking.dto.GenreDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GenreDto
import com.whgarcia.gameslib.game.data.networking.dto.ParentPlatformDto
import com.whgarcia.gameslib.game.data.networking.dto.PlatformDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.PlatformDto
import com.whgarcia.gameslib.game.data.networking.dto.PlatformsDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.PublisherDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.RatingDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.TagDetailDto
import com.whgarcia.gameslib.game.domain.DeveloperDetailDomain
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GameDetail
import com.whgarcia.gameslib.game.domain.GenreDetailDomain
import com.whgarcia.gameslib.game.domain.GenreDomain
import com.whgarcia.gameslib.game.domain.ParentPlatformDomain
import com.whgarcia.gameslib.game.domain.PlatformDetailDomain
import com.whgarcia.gameslib.game.domain.PlatformDomain
import com.whgarcia.gameslib.game.domain.PlatformsDetailDomain
import com.whgarcia.gameslib.game.domain.PublisherDetailDomain
import com.whgarcia.gameslib.game.domain.RatingDetailDomain
import com.whgarcia.gameslib.game.domain.TagDetailDomain

// Función de extensión para mapear GameDto a Game
fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name ?: "",
        released = released ?: "",
        background_image = background_image ?: "",
        parent_platforms = parent_platforms.map { it.toParentPlatformDomain() },
        genres = genres.map { it.toGenreDomain() }
    )
}

// Función de extensión para mapear ParentPlatformDto a ParentPlatformDomain
fun ParentPlatformDto.toParentPlatformDomain(): ParentPlatformDomain {
    return ParentPlatformDomain(
        platform = platform.toPlatformDomain()
    )
}

// Función de extensión para mapear PlatformDto a PlatformDomain
fun PlatformDto.toPlatformDomain(): PlatformDomain {
    return PlatformDomain(
        id = id,
        slug = slug
    )
}

// Función de extensión para mapear GenreDto a GenreDomain
fun GenreDto.toGenreDomain(): GenreDomain{
    return GenreDomain(
        id = id,
        name = name
    )
}

// Función de extensión para mapear GameDetailDto a GameDetail
fun GameDetailDto.toGameDetail(): GameDetail {
    return GameDetail(
        name = name ?: "",
        background_image = background_image ?: "",
        description_raw = description_raw ?: "",
        released = released ?: "",
        metacritic = metacritic ?: -1,
        website = website ?: "",
        rating_top = rating_top ?: -1,
        ratings = ratings.map { it.toRatingDetailDomain() },
        platforms = platforms.map { it.toPlatformsDetailDomain() },
        developers = developers.map { it.toDeveloperDetailDomain() },
        genres = genres.map { it.toGenreDetailDomain() },
        tags = tags.map { it.toTagDetailDomain() },
        publishers = publishers.map { it.toPublisherDetailDomain() }
    )
}

// Función de extensión para mapear RatingDetailDto a RatingDetailDomain
fun RatingDetailDto.toRatingDetailDomain(): RatingDetailDomain{
    return RatingDetailDomain(
        id = id,
        title = title,
        count = count
    )
}

// Función de extensión para mapear PlatformDetailDto a PlatformDetailDomain
fun PlatformDetailDto.toPlatformDetailDomain(): PlatformDetailDomain{
    return PlatformDetailDomain(
        id = id,
        name = name
    )
}

fun PlatformsDetailDto.toPlatformsDetailDomain(): PlatformsDetailDomain {
    return PlatformsDetailDomain(
        platform = platform.toPlatformDetailDomain()
    )
}

// Función de extensión para mapear DeveloperDetailDto a DeveloperDetailDomain
fun DeveloperDetailDto.toDeveloperDetailDomain(): DeveloperDetailDomain{
    return DeveloperDetailDomain(
        id = id,
        name = name
    )
}

// Función de extensión para mapear GenreDetailDto a GenreDetailDomain
fun GenreDetailDto.toGenreDetailDomain(): GenreDetailDomain{
    return GenreDetailDomain(
        id = id,
        name = name
    )
}

// Función de extensión para mapear TagDetailDto a TagDetailDomain
fun TagDetailDto.toTagDetailDomain(): TagDetailDomain{
    return TagDetailDomain(
        id = id,
        name = name
    )
}

// Función de extensión para mapear PublisherDetailDto a PublisherDetailDomain
fun PublisherDetailDto.toPublisherDetailDomain(): PublisherDetailDomain{
    return PublisherDetailDomain(
        id = id,
        name = name
    )
}