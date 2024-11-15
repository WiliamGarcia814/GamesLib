package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.whgarcia.gameslib.game.domain.Game
import com.whgarcia.gameslib.game.domain.GenreDomain
import com.whgarcia.gameslib.game.domain.ParentPlatformDomain
import com.whgarcia.gameslib.game.domain.PlatformDomain
import com.whgarcia.gameslib.game.presentation.models.GameUi
import com.whgarcia.gameslib.game.presentation.models.toGameUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameListItem(
    gameUi: GameUi,
    onClick: () -> Unit,
    modifier: Modifier
){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(vertical = 8.dp)
            .shadow(40.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            GameCardImage(gameUi.background_image)
            GameCardInformation(gameUi = gameUi)
        }
    }
}

internal val previewGame = Game(
    id = 1,
    name = "Grand Theft Auto V",
    released = "2024-11-27",
    background_image = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
    parent_platforms = listOf(
        ParentPlatformDomain(
            PlatformDomain(
                id = 1,
                slug = "pc"
            )
        ),
        ParentPlatformDomain(
            PlatformDomain(
                id = 2,
                slug = "playstation"
            )
        ),
        ParentPlatformDomain(
            PlatformDomain(
                id = 2,
                slug = "xbox"
            )
        )
    ),
    genres = listOf(
        GenreDomain(
            id = 1,
            name = "Action"
        ),
        GenreDomain(
            id = 1,
            name = "Adventure"
        )
    )
).toGameUi()

@PreviewLightDark
@Composable
fun GameListItemPreview(){
    GamesLibTheme {
        GameListItem(
            gameUi = previewGame,
            onClick = {},
            modifier = Modifier.background(
                MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}