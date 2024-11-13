package com.whgarcia.gameslib.game.presentation.game_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Nombre del juego
        Text(
            text = gameDetailUi.name,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 40.sp
        )
        // Calificación del juego
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ){
            Column(
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = titleRating,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$totalRatings "+stringResource(R.string.ratings),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            ReviewCard(metascore = gameDetailUi.metacritic)
        }
        HorizontalDivider()
    }
}

@Composable
fun ReviewCard(metascore: Int){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = if(metascore in 70..100) Color.Green else if(metascore in 31..69) Color.Yellow else if(metascore in 0..30) Color.Red else MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(
            text = if(metascore == -1) stringResource(R.string.no_metascore) else metascore.toString(),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
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