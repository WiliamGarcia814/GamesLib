package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.game_list.GameListAction
import com.whgarcia.gameslib.game.presentation.game_list.GameListState
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GamesSearchBar(
    state: GameListState,
    onAction: (GameListAction) -> Unit,
    modifier: Modifier
){
    var searchText by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                // Hacer petición
                onAction(GameListAction.SearchGames(searchText))
                isDropdownExpanded = it.isNotEmpty()
            },
            label = { Text(stringResource(R.string.search_games)) },
            modifier = Modifier.fillMaxWidth()
        )

        if(isDropdownExpanded && state.searchGames.isNotEmpty()){
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){
                LazyColumn {
                    items(state.searchGames) { gameUi ->
                        DropdownMenuItem(
                            text = { Text(gameUi.name) },
                            onClick = {
                                isDropdownExpanded = false
                                onAction(GameListAction.OnGameClick(gameUi))
                                searchText = ""
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun GamesSearchBarPreview(){
    GamesLibTheme {
        GamesSearchBar(
            state = GameListState(
               games = (1..3).map {
                   previewGame.copy(id = it)
               }
            ),
            onAction = {},
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}