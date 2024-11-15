package com.whgarcia.gameslib.game.presentation.game_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.models.GameScreenshotUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameScreenshots(
    gameScreenshots: List<GameScreenshotUi>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.screenshots),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(gameScreenshots.size){ screenShot ->
                // Si la imagen está borrada, no se muestra
                if(gameScreenshots[screenShot].is_deleted) return@items
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(gameScreenshots[screenShot].image),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .width(300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surface)
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun GameScreenshotsPreview(){
    GamesLibTheme {
        GameScreenshots(
            gameScreenshots = (1..3).map {
                GameScreenshotUi(
                    id = it,
                    image = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                    is_deleted = false
                )
            }
        )
    }
}