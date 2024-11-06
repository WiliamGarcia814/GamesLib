package com.whgarcia.gameslib.game.presentation.game_list

import com.whgarcia.gameslib.core.domain.util.NetworkError

sealed interface GameListEvent {
    data class Error(val error: NetworkError): GameListEvent
}