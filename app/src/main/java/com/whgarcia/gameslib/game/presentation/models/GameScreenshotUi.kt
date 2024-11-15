package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.GameScreenshot

data class GameScreenshotUi(
    val id: Int,
    val image: String,
    val is_deleted: Boolean
)

fun GameScreenshot.toGameScreenshotUi(): GameScreenshotUi {
    return GameScreenshotUi(
        id = id,
        image = image,
        is_deleted = is_deleted
    )
}