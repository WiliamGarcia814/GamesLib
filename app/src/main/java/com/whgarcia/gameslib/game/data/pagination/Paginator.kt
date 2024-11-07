package com.whgarcia.gameslib.game.data.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextGames()
    fun reset()
}