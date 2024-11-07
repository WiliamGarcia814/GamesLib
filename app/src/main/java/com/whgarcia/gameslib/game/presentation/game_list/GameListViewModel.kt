package com.whgarcia.gameslib.game.presentation.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whgarcia.gameslib.core.domain.util.map
import com.whgarcia.gameslib.core.domain.util.onError
import com.whgarcia.gameslib.core.domain.util.onSuccess
import com.whgarcia.gameslib.game.data.pagination.DefaultPaginator
import com.whgarcia.gameslib.game.domain.GameDataSource
import com.whgarcia.gameslib.game.presentation.models.GameUi
import com.whgarcia.gameslib.game.presentation.models.toGameDetailUi
import com.whgarcia.gameslib.game.presentation.models.toGameUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameListViewModel(
    private val gameDataSource: GameDataSource
): ViewModel() {

    private val _state = MutableStateFlow(GameListState())
    val state = _state
        .onStart { loadMoreGames() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GameListState()
        )

    private val _events = Channel<GameListEvent>()
    val events = _events.receiveAsFlow()

    private var paginator: DefaultPaginator<Int, GameUi> = DefaultPaginator(
        initialKey = 1,
        onLoadUpdated = { isLoading ->
            _state.update { it.copy(isLoading = isLoading) }
        },
        onRequest = { page ->
            gameDataSource.getGames(page, 3)
                .map { games -> games.map { it.toGameUi() } }
        },
        getNextKey = { games, currentPage ->
            if (games.isEmpty()){
                _state.update { it.copy(isEndReached = true) }
                currentPage // No incrementa si no hay más juegos
            }else {
                currentPage + 1
            }
        },
        onError = { error ->
            viewModelScope.launch {
                _events.send(GameListEvent.Error(error))
            }
        },
        onSuccess = { games ->
            _state.update { currentState ->
                currentState.copy(games = currentState.games + games)
            }
        }
    )

    fun onAction(action: GameListAction){
        when(action){
            is GameListAction.OnGameClick -> {
                selectedGame(action.gameUi)
            }
            GameListAction.LoadNextPage -> loadMoreGames()
        }
    }

    private fun loadMoreGames(){
        viewModelScope.launch {
            if(!state.value.isEndReached){
                paginator.loadNextGames()
            }
        }
    }

    private fun selectedGame(gameUi: GameUi){
        _state.update { it.copy(
            selectedGame = gameUi,
            isDetailLoading = true
        ) }

        viewModelScope.launch {
            gameDataSource
                .getGameById(gameUi.id)
                .onSuccess { game ->
                    _state.update {
                        it.copy(
                            selectedGameDetail = game.toGameDetailUi(),
                            isDetailLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isDetailLoading = false) }
                    _events.send(GameListEvent.Error(error))
                }
        }
    }
}