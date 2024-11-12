package com.whgarcia.gameslib.game.presentation.models

import com.whgarcia.gameslib.game.domain.Game
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class GameUi(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val parent_platforms: List<String>,
    val genres: List<String>
)


fun Game.toGameUi(): GameUi{
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())

    val formattedDate = try {
        LocalDate.parse(released, inputFormatter).format(outputFormatter)
    }catch (e: Exception){
        released
    }

    return GameUi(
        id = id,
        name = name,
        released = formattedDate,
        background_image = background_image,
        parent_platforms = parent_platforms.map { it.platform.slug },
        genres = genres.map { it.name }
    )
}