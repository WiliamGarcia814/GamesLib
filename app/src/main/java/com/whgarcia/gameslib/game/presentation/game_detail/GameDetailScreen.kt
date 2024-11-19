package com.whgarcia.gameslib.game.presentation.game_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.whgarcia.gameslib.game.domain.DeveloperDetailDomain
import com.whgarcia.gameslib.game.domain.GameDetail
import com.whgarcia.gameslib.game.domain.GenreDetailDomain
import com.whgarcia.gameslib.game.domain.PlatformDetailDomain
import com.whgarcia.gameslib.game.domain.PlatformsDetailDomain
import com.whgarcia.gameslib.game.domain.PublisherDetailDomain
import com.whgarcia.gameslib.game.domain.RatingDetailDomain
import com.whgarcia.gameslib.game.domain.TagDetailDomain
import com.whgarcia.gameslib.game.presentation.game_detail.components.GameInformation
import com.whgarcia.gameslib.game.presentation.game_detail.components.GameMetrics
import com.whgarcia.gameslib.game.presentation.game_detail.components.GameScreenshots
import com.whgarcia.gameslib.game.presentation.game_list.GameListAction
import com.whgarcia.gameslib.game.presentation.game_list.GameListState
import com.whgarcia.gameslib.game.presentation.models.GameScreenshotUi
import com.whgarcia.gameslib.game.presentation.models.toGameDetailUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameDetailScreen(
    state: GameListState,
    modifier: Modifier = Modifier,
    onAction: (GameListAction) -> Unit
){
    if (state.isDetailLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else if(state.selectedGameDetail != null){
        val gameDetailUi = state.selectedGameDetail
        val gameScreenshots = state.selectedGameScreenshot
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameMetrics(gameDetailUi)
            GameScreenshots(gameScreenshots)
            GameInformation(gameDetailUi)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            onAction(GameListAction.clear)
        }
    }
}

internal val previewGameDetail = GameDetail(
    name = "Grand Theft Auto V",
    background_image = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
    description_raw = "Grand Theft Auto V es un videojuego de acción-aventura de mundo abierto desarrollado por Rockstar North y publicado por Rockstar Games. ",
    metacritic = 92,
    released = "2024-11-27",
    website = "https://www.rockstargames.com/games/grand-theft-auto-v",
    rating_top = 5,
    ratings = listOf(
        RatingDetailDomain(
            id = 5,
            title = "exceptional",
            count = 100,
        ),
        RatingDetailDomain(
            id = 4,
            title = "recommended",
            count = 50,
        ),
    ),
    platforms = listOf(
        PlatformsDetailDomain(
            platform = PlatformDetailDomain(
                id = 1,
                name = "PC"
            )
        ),
        PlatformsDetailDomain(
            platform = PlatformDetailDomain(
                id = 2,
                name = "PlayStation 4"
            )
        )
    ),
    developers = listOf(
        DeveloperDetailDomain(
            id = 1,
            name = "Rockstar Games"
        )
    ),
    genres = listOf(
        GenreDetailDomain(
            id = 1,
            name = "Action"
        ),
        GenreDetailDomain(
            id = 2,
            name = "Adventure"
        )
    ),
    tags = listOf(
        TagDetailDomain(
            id = 1,
            name = "Open World"
        ),
        TagDetailDomain(
            id = 2,
            name = "Singleplayer"
        )
    ),
    publishers = listOf(
        PublisherDetailDomain(
            id = 1,
            name = "Rockstar Games"
        )
    )
).toGameDetailUi()

@PreviewLightDark
@Composable
fun GameDetailScreenPreview(){
    GamesLibTheme {
        GameDetailScreen(
            state = GameListState(
                selectedGameDetail = previewGameDetail,
                selectedGameScreenshot = (1..3).map {
                    GameScreenshotUi(
                        id = it,
                        image = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                        is_deleted = false
                    )
                }
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            ),
            {}
        )
    }
}