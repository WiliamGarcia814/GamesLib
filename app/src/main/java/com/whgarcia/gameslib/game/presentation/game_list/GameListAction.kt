package com.whgarcia.gameslib.game.presentation.game_list

import com.whgarcia.gameslib.game.presentation.models.GameUi

sealed interface GameListAction {
    data class OnGameClick(val gameUi: GameUi): GameListAction
}