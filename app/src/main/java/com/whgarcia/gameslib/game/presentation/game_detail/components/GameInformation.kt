package com.whgarcia.gameslib.game.presentation.game_detail.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whgarcia.gameslib.R
import com.whgarcia.gameslib.game.presentation.game_detail.previewGameDetail
import com.whgarcia.gameslib.game.presentation.models.GameDetailUi
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameInformation(
    gameDetailUi: GameDetailUi
){
    var isExpandedDescription by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        // Descripción del juego
        if(gameDetailUi.description_raw.isNotEmpty()){
            Text(
                text = stringResource(R.string.about_the_game),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = gameDetailUi.description_raw,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = if (isExpandedDescription) Int.MAX_VALUE else 8,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = if (isExpandedDescription) stringResource(R.string.read_less) else stringResource(
                        R.string.read_more
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .clickable { isExpandedDescription = !isExpandedDescription }
                )
            }
        }

        // Plataformas y géneros
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.platforms),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if(gameDetailUi.platforms.isNotEmpty()){
                    Text(
                        text = gameDetailUi.platforms.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.genres),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if(gameDetailUi.genres.isNotEmpty()) {
                    Text(
                        text = gameDetailUi.genres.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Fecha de lanzamiento y desarrolladores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.released_date),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if (gameDetailUi.released.isNotEmpty()) {
                    Text(
                        text = gameDetailUi.released,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.developers),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if(gameDetailUi.developers.isNotEmpty()) {
                    Text(
                        text = gameDetailUi.developers.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Editor y sitio web
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.publishers),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if(gameDetailUi.publishers.isNotEmpty()) {
                    Text(
                        text = gameDetailUi.publishers.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.website),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if (gameDetailUi.website.isNotEmpty()) {
                    val context = LocalContext.current
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(gameDetailUi.website))
                    Box(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.onSurface)
                            .padding(horizontal = 4.dp)
                            .clickable {
                                context.startActivity(intent)
                            }
                    ) {
                        Text(
                            text = stringResource(R.string.open_website),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        }

        // Tags
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column {
                Text(
                    text = stringResource(R.string.tags),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.outline
                )
                if(gameDetailUi.tags.isNotEmpty()) {
                    Text(
                        text = gameDetailUi.tags.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun GameInformationPreview(){
    GamesLibTheme {
        GameInformation(
            previewGameDetail
        )
    }
}