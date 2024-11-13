package com.whgarcia.gameslib.game.data.pagination

import com.whgarcia.gameslib.core.domain.util.NetworkError
import com.whgarcia.gameslib.core.domain.util.Result
import com.whgarcia.gameslib.core.domain.util.onError
import com.whgarcia.gameslib.core.domain.util.onSuccess

// Interfaz para un paginador
class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (Key) -> Result<List<Item>, NetworkError>,
    private val getNextKey: suspend (List<Item>, Key) -> Key,
    private val onError: (NetworkError) -> Unit,
    private val onSuccess: (List<Item>) -> Unit
): Paginator<Key, Item>{

    // Propiedades para el paginador
    private var currentKey = initialKey
    private var isMakingRequest = false

    // Implementación de la función para cargar más juegos
    override suspend fun loadNextGames() {
        if(isMakingRequest) return

        onLoadUpdated(true)
        isMakingRequest = true

        onRequest(currentKey)
            .onSuccess{ items ->
                currentKey = getNextKey(items, currentKey)
                onSuccess(items)
            }
            .onError { error ->
                onError(error)
            }

        isMakingRequest = false
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }

}