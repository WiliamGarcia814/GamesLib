package com.whgarcia.gameslib.game.presentation.game_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whgarcia.gameslib.game.presentation.game_detail.components.GameMetrics
import com.whgarcia.gameslib.game.presentation.game_list.GameListState
import com.whgarcia.gameslib.game.presentation.game_list.components.previewGame
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameDetailScreen(
    state: GameListState,
    modifier: Modifier = Modifier
){
    if (state.isDetailLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else if(state.selectedGame != null){
        val game = state.selectedGameDetail
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Agregar componente de imagenes
            Spacer(modifier = Modifier.height(16.dp))
            GameMetrics(
                name = game?.name ?: "",
                website = game?.website ?: "",
                metascore = game?.metacritic ?: 0,
                modifier = modifier
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = game?.description_raw ?: "",
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GameDetailScreenPreview(){
    GamesLibTheme {
        GameDetailScreen(
            state = GameListState(
                selectedGame = previewGame,
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}