package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.models.GameUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameCardInformation(gameUi: GameUi){
    // Mapa de plataformas
    val platformIcons = mapOf(
        "pc" to Pair(R.drawable.ic_pc_black, R.drawable.ic_pc_white),
        "mac" to Pair(R.drawable.ic_apple_black, R.drawable.ic_apple_white),
        "linux" to Pair(R.drawable.ic_linux_black, R.drawable.ic_linux_white),
        "android" to Pair(R.drawable.ic_android_black, R.drawable.ic_android_white),
        "ios" to Pair(R.drawable.ic_ios_black, R.drawable.ic_ios_white),
        "playstation" to Pair(R.drawable.ic_playstation_black, R.drawable.ic_playstation_white),
        "xbox" to Pair(R.drawable.ic_xbox_black, R.drawable.ic_xbox_white),
        "nintendo" to Pair(R.drawable.ic_nintendo_black, R.drawable.ic_nintendo_white),
        "atari" to Pair(R.drawable.ic_atari_black, R.drawable.ic_atari_white),
        "sega" to Pair(R.drawable.ic_sega_black, R.drawable.ic_sega_white),
        "web" to Pair(R.drawable.ic_web_black, R.drawable.ic_web_white),
    )

    // Estado para manejar la visibilidad de los géneros
    var showMore by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyRow {
            items(gameUi.parent_platforms.size) { i ->
                val platform = gameUi.parent_platforms[i]
                val (iconDark, iconLight) = platformIcons[platform] ?: return@items
                Icon(
                    painter = painterResource(if (isSystemInDarkTheme()) iconDark else iconLight),
                    contentDescription = platform,
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .size(16.dp)
                )
            }
        }
        Text(
            text = gameUi.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
        // Mostrar los géneros si showGenres es true
        if (showMore) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.released_date),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = gameUi.released,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.genres),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = gameUi.genres.joinToString(", "),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider()

        }
        // Botón de Show More / Show Less
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    showMore = !showMore
                }
            ) {
                Text(
                    text = if (showMore) stringResource(R.string.show_less) else stringResource(R.string.show_more),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun GameCardInformationPreview(){
    GamesLibTheme {
        GameCardInformation(
            gameUi = GameUi(
                id = 1,
                name = "Grand Theft Auto V",
                released = "2024-11-27",
                background_image = "",
                parent_platforms = listOf("pc", "playstation", "xbox", "3do"),
                genres = listOf("Action", "Adventure")
            )
        )
    }
}