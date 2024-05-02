package com.muratcangzm.rickandmortyapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratcangzm.network.KtorClient


@Composable
fun CharacterEpisodeScreen(characterId: Int, ktorClient: KtorClient) {

    LaunchedEffect(key1 = Unit, block = {

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Character ID is $characterId",
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .border(
                    width = 1.dp, color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )
    }
}