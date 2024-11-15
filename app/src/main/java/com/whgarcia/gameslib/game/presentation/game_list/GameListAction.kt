package com.whgarcia.gameslib.game.presentation.game_list


sealed interface GameListAction {
    data class OnGameClick(val id: Int): GameListAction
    data class SearchGames(val search: String): GameListAction
    object LoadNextPage : GameListAction
}