package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.GameSearch

data class GameSearchUi(
    val id: Int,
    val name: String,
    val background_image: String
)

fun GameSearch.toGameSearchUi(): GameSearchUi{
    return GameSearchUi(
        id = id,
        name = name,
        background_image = background_image
    )
}