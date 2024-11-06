package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.whgarcia.gameslib.game.domain.Game
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
            .padding(16.dp)
            .shadow(40.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            BackgroundImage(gameUi.background_image)
            Text(
                text = gameUi.name,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
fun BackgroundImage(image: String, height: Dp = 180.dp, padding: Dp = 16.dp){
    val image = rememberAsyncImagePainter(model = image)
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(padding)
            .shadow(40.dp)
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            )
        }
    }
}

internal val previewGame = Game(
    id = 1,
    name = "Grand Theft Auto V",
    background_image = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg"
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