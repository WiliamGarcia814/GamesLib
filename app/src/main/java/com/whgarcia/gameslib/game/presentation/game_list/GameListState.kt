package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.runtime.Immutable
import com.whgarcia.gameslib.game.presentation.models.GameUi

@Immutable
data class GameListState(
    val isLoading: Boolean = false,
    val games: List<GameUi> = emptyList(),
    val selectedGame: GameUi? = null
)
