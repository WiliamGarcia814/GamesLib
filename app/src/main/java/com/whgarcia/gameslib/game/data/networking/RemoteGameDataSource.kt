package com.whgarcia.gameslib.game.data.networking

import com.whgarcia.gameslib.BuildConfig
import com.whgarcia.gameslib.core.data.networking.constructUrl
import com.whgarcia.gameslib.core.data.networking.safeCall
import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result
import com.whgarcia.gameslib.core.domain.util.map
import com.whgarcia.gameslib.game.data.mappers.toGame
import com.whgarcia.gameslib.game.data.mappers.toGameDetail
import com.whgarcia.gameslib.game.data.mappers.toGameScreenshot
import com.whgarcia.gameslib.game.data.mappers.toGameSearch
import com.whgarcia.gameslib.game.data.networking.dto.GameDetailDto
import com.whgarcia.gameslib.game.data.networking.dto.GameScreenshotResponseDto
import com.whgarcia.gameslib.game.data.networking.dto.GamesResponseDto
import com.whgarcia.gameslib.game.data.networking.dto.GamesSearchResponseDto
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GameDataSource
import com.whgarcia.gameslib.game.domain.GameDetail
import com.whgarcia.gameslib.game.domain.GameScreenshot
import com.whgarcia.gameslib.game.domain.GameSearch
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteGameDataSource(
    private val httpClient: HttpClient
): GameDataSource{
    // Implementaciones de las funciones de GameDataSource
    override suspend fun getGames(page: Int, page_size: Int): Result<List<Game>, NetworkError> {
        return safeCall<GamesResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games${BuildConfig.API_KEY}")
            ){
                parameter("page", page)
                parameter("page_size", page_size)
            }
        }.map { response ->
            response.results.map { it.toGame() }
        }
    }

    override suspend fun getSearchGames(search: String): Result<List<GameSearch>, NetworkError> {
        return safeCall<GamesSearchResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games${BuildConfig.API_KEY}")
            ){
                parameter("search", search)
            }
        }.map { response ->
            response.results.map { it.toGameSearch() }
        }
    }

    override suspend fun getGameById(gameId: Int): Result<GameDetail, NetworkError> {
        return safeCall<GameDetailDto> {
            httpClient.get(
                urlString = constructUrl("/games/${gameId}${BuildConfig.API_KEY}")
            )
        }.map { gameDetailDto ->
            gameDetailDto.toGameDetail()
        }
    }

    override suspend fun getGameScreenshots(gameId: Int): Result<List<GameScreenshot>, NetworkError> {
        return safeCall<GameScreenshotResponseDto> {
            httpClient.get(
                urlString = constructUrl("/games/${gameId}/screenshots${BuildConfig.API_KEY}")
            )
        }.map { response ->
            response.results.map { it.toGameScreenshot() }
        }
    }
}