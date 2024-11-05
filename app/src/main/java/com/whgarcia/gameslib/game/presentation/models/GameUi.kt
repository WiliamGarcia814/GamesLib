package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.Game

data class GameUi(
    val id: Int,
    val name: String,
    val background_image: String
)


fun Game.toGameUi(): GameUi{
    return GameUi(
        id = id,
        name = name,
        background_image = background_image
    )
}