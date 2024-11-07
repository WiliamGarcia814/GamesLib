package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.whgarcia.gameslib.game.presentation.game_list.components.GameListItem

@Composable
fun GameListScreen(
    viewModel: GameListViewModel,
    onAction: (GameListAction) -> Unit,
    modifier: Modifier = Modifier
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    if (state.isListLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.games.size) { i ->
                val gameUi = state.games[i]
                if (i >= state.games.size - 1 && !state.isEndReached && !state.isLoading){
                    viewModel.onAction(GameListAction.LoadNextPage)
                }
                GameListItem(
                    gameUi = gameUi,
                    onClick = { onAction(GameListAction.OnGameClick(gameUi)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                if (state.isLoading){
                    Box(
                        modifier = modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}