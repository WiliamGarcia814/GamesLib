package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.whgarcia.gameslib.game.presentation.game_list.components.GameListItem
import com.whgarcia.gameslib.game.presentation.game_list.components.GamesSearchBar
import com.whgarcia.gameslib.game.presentation.game_list.components.previewGame
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameListScreen(
    state: GameListState,
    onAction: (GameListAction) -> Unit,
    modifier: Modifier = Modifier
){
    if (state.isListLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            GamesSearchBar(
                state = state,
                onAction = onAction,
                modifier = modifier
            )

            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.games.size) { i ->
                    val gameUi = state.games[i]
                    if (i >= state.games.size - 1 && !state.isEndReached && !state.isLoading){
                        onAction(GameListAction.LoadNextPage)
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
}

@PreviewLightDark
@Composable
private fun GameListScreenPreview(){
    GamesLibTheme {
        GameListScreen(
            state = GameListState(
                games = (1..3).map {
                    previewGame.copy(id = it)
                }
            ),
            onAction = {},
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background
                )
        )
    }
}