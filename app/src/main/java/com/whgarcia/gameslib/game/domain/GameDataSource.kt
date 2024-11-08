package com.whgarcia.gameslib.game.domain

import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result

interface GameDataSource {
    suspend fun getGames(
        page: Int,
        page_size: Int
    ): Result<List<Game>, NetworkError>

    suspend fun getSearchGames(
        search: String
    ): Result<List<Game>, NetworkError>

    suspend fun getGameById(
        gameId: Int
    ): Result<GameDetail, NetworkError>
}