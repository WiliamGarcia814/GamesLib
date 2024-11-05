package com.whgarcia.gameslib.game.presentation.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whgarcia.gameslib.core.domain.util.onError
import com.whgarcia.gameslib.core.domain.util.onSuccess
import com.whgarcia.gameslib.game.domain.GameDataSource
import com.whgarcia.gameslib.game.presentation.models.toGameUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameListViewModel(
    private val gameDataSource: GameDataSource
): ViewModel() {

    private val _state = MutableStateFlow(GameListState())
    val state = _state
        .onStart { loadGames() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            GameListState()
        )

    fun onAction(action: GameListAction){
        when(action){
            is GameListAction.OnGameClick -> {

            }
        }
    }

    private fun loadGames(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            gameDataSource
                .getGames()
                .onSuccess { games ->
                    _state.update { it.copy(
                        isLoading = false,
                        games = games.map { it.toGameUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}