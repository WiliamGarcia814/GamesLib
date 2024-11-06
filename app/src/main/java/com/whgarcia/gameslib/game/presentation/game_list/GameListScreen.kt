package com.whgarcia.gameslib.game.presentation.game_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.whgarcia.gameslib.game.presentation.game_list.components.GameListItem
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.games) { gameUi ->
                GameListItem(
                    gameUi = gameUi,
                    onClick = { onAction(GameListAction.OnGameClick(gameUi)) },
                    modifier = Modifier.fillMaxWidth()
                )
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
                games = (1..10).map {
                    previewGame.copy(id = it)
                }
            ),
            onAction = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}