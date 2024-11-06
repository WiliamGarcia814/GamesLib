package com.whgarcia.gameslib.game.presentation.game_detail.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whgarcia.gameslib.ui.theme.GamesLibTheme

@Composable
fun GameMetrics(
    name: String,
    website: String,
    metascore: Int,
    modifier: Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        MetaWebsite(url = website, name = name, modifier = Modifier.weight(2f))
        ReviewCard(metascore = metascore, modifier = Modifier.weight(1f))
    }
}

@Composable
fun MetaWebsite(url: String, name: String, modifier: Modifier){

    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
    ){
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 10.dp),
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 30.sp
        )

        Button(
            onClick = { context.startActivity(intent) }
        ) {
            Text(text = "Sitio Web")
        }
    }
}

@Composable
fun ReviewCard(metascore: Int, modifier: Modifier){
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.White,
                containerColor = if(metascore in 70..100) Color.Green else if(metascore in 31..69) Color.Yellow else if(metascore in 0..30) Color.Red else MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = if(metascore in 0..100) metascore.toString() else "N/A",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 50.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GameMetricsPreview(){
    GamesLibTheme {
        GameMetrics(
            "Nombre",
            "Website",
            100,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background
                )
        )
    }
}