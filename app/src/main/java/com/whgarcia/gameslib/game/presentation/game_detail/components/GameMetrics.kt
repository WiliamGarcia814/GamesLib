package com.whgarcia.gameslib.game.presentation.game_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.game_detail.previewGameDetail
import com.whgarcia.gameslib.game.presentation.models.GameDetailUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameMetrics(
    gameDetailUi: GameDetailUi
){
    val totalRatings = gameDetailUi.ratings.sumOf { it.count }
    val titleRatingOfTop = gameDetailUi.ratings.find { it.id == gameDetailUi.rating_top }
    val titleRating = when(titleRatingOfTop?.title){
        "exceptional" -> stringResource(R.string.exceptional)
        "recommended" -> stringResource(R.string.recommended)
        "meh" -> stringResource(R.string.meh)
        "skip" -> stringResource(R.string.skip)
        else -> stringResource(R.string.no_rating)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f) // Mantiene la proporción 16:9 para la imagen
        ) {
            Image(
                painter = rememberAsyncImagePainter(gameDetailUi.background_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 0f,
                            endY = 600f
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Contenido superpuesto
            Text(
                text = gameDetailUi.name,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 40.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = titleRating,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "$totalRatings " + stringResource(R.string.ratings),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                ReviewCard(metascore = gameDetailUi.metacritic)
            }
        }
    }
}

@Composable
fun ReviewCard(metascore: Int){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = if(metascore in 70..100) Color.Green else if(metascore in 31..69) Color.Yellow else if(metascore in 0..30) Color.Red else MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = if(metascore == -1) stringResource(R.string.no_metascore) else metascore.toString(),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(6.dp)
        )
    }
}

@PreviewLightDark
@Composable
fun GameMetricsPreview(){
    GamesLibTheme {
        GameMetrics(
            previewGameDetail
        )
    }
}