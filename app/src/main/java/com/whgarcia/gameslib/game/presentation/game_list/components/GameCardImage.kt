package com.whgarcia.gameslib.game.presentation.game_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameCardImage(image: String){
    Card(
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}

@PreviewLightDark
@Composable
fun GameBackgroundImagePreview(){
    GamesLibTheme {
        GameCardImage(
            image = previewGame.background_image
        )
    }
}