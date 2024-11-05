package com.whgarcia.gameslib.game.data.networking

import com.whgarcia.gameslib.BuildConfig
import com.whgarcia.gameslib.core.data.networking.constructUrl
import com.whgarcia.gameslib.core.data.networking.safeCall
import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result
import com.whgarcia.gameslib.core.domain.util.map
import com.whgarcia.gameslib.game.data.mappers.toGame
import com.whgarcia.gameslib.game.data.networking.dto.GamesResponseDto
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GameDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteGameDataSource(
    private val httpClient: HttpClient
): GameDataSource{
    override suspend fun getGames(): Result<List<Game>, NetworkError> {
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games${BuildConfig.API_KEY}")
            )
        }.map { response ->
            response.results.map { it.toGame() }
        }
    }
}