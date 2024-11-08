package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.runtime.Immutable
import com.whgarcia.gameslib.game.presentation.models.GameDetailUi
import com.whgarcia.gameslib.game.presentation.models.GameUi

@Immutable
data class GameListState(
    val games: List<GameUi> = emptyList(),
    val searchGames: List<GameUi> = emptyList(),
    val selectedGame: GameUi? = null,
    val selectedGameDetail: GameDetailUi? = null,
    val isListLoading: Boolean = false,
    val isDetailLoading: Boolean = false,
    val isSearchLoading: Boolean = false,
    val isLoading: Boolean = false,
    val isEndReached: Boolean = false
)
