package com.whgarcia.gameslib.game.data.mappers

import com.whgarcia.gameslib.game.data.networking.dto.GameDto
import com.whgarcia.gameslib.game.domain.Game

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        name = name,
        background_image = background_image
    )
}