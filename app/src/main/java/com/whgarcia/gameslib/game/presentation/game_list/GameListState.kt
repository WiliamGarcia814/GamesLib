package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Immutable
import com.whgarcia.gameslib.game.presentation.models.GameDetailUi
import com.whgarcia.gameslib.game.presentation.models.GameScreenshotUi
import com.whgarcia.gameslib.game.presentation.models.GameSearchUi
import com.whgarcia.gameslib.game.presentation.models.GameUi

@Immutable
data class GameListState(
    val games: List<GameUi> = emptyList(),
    val searchGames: List<GameSearchUi> = emptyList(),
    val listState: LazyListState = LazyListState(),
    val selectedGameDetail: GameDetailUi? = null,
    val selectedGameScreenshot: List<GameScreenshotUi> = emptyList(),
    val isListLoading: Boolean = true,
    val isDetailLoading: Boolean = false,
    val isLoading: Boolean = false,
    val isEndReached: Boolean = false
)
