package com.whgarcia.gameslib.game.data.mappers

import com.whgarcia.gameslib.game.data.networking.dto.GameDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GameDto
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GameDetail

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name ?: "",
        background_image = background_image ?: ""
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