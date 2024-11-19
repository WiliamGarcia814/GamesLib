package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.game_list.GameListAction
import com.whgarcia.gameslib.game.presentation.game_list.GameListState
import com.whgarcia.gameslib.game.presentation.models.GameSearchUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GamesSearchBar(
    state: GameListState,
    onAction: (GameListAction) -> Unit
){
    var searchText by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                // Hacer petición
                onAction(GameListAction.SearchGames(searchText))
                isDropdownExpanded = it.isNotEmpty()
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_games),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            },
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline
            )
        )

        if(isDropdownExpanded && state.searchGames.isNotEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){
                LazyColumn {
                    items(state.searchGames) { gameUi ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(model = gameUi.background_image),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .height(50.dp)
                                                .width(50.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp)) // Espacio entre imagen y texto

                                    // Texto del nombre del juego
                                    Text(
                                        text = gameUi.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            },
                            onClick = {
                                isDropdownExpanded = false
                                onAction(GameListAction.OnGameClick(gameUi.id))
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
               searchGames = (1..3).map {
                   GameSearchUi(
                       id = it,
                       name = "Grand Theft Auto V",
                       background_image = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"
                   )
               }
            ),
            onAction = {}
        )
    }
}