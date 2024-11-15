package com.whgarcia.gameslib.game.domain

import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result

interface GameDataSource {
    // Declaración de la función para obtener juegos
    suspend fun getGames(
        page: Int,
        page_size: Int
    ): Result<List<Game>, NetworkError>

    // Declaración de la función para obtener juegos por búsqueda
    suspend fun getSearchGames(
        search: String
    ): Result<List<GameSearch>, NetworkError>

    // Declaración de la función para obtener detalles de un juego por su ID
    suspend fun getGameById(
        gameId: Int
    ): Result<GameDetail, NetworkError>
}