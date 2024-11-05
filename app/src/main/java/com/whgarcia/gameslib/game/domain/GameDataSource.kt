package com.whgarcia.gameslib.game.domain

import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result

interface GameDataSource {
    suspend fun getGames(): Result<List<Game>, NetworkError>
}