package com.muratcangzm.rickandmortyapp.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratcangzm.network.KtorClient
import com.muratcangzm.network.models.domain.Character
import com.muratcangzm.network.models.domain.Episode
import com.muratcangzm.rickandmortyapp.components.common.CharacterImage
import com.muratcangzm.rickandmortyapp.components.common.CharacterNameComponent
import com.muratcangzm.rickandmortyapp.components.common.LoadingState
import com.muratcangzm.rickandmortyapp.components.episode.EpisodeRowComponent
import com.muratcangzm.rickandmortyapp.ui.theme.RickPrimary
import com.muratcangzm.rickandmortyapp.ui.theme.RickTextPrimary
import kotlinx.coroutines.launch


@Composable
fun CharacterEpisodeScreen(characterId: Int, ktorClient: KtorClient) {

    var characterState by remember {
        mutableStateOf<Character?>(null)
    }
    var episodesState by remember {
        mutableStateOf<List<Episode>>(emptyList())
    }

    LaunchedEffect(key1 = Unit, block = {
        ktorClient.getCharacter(characterId).onSuccess { character ->
            characterState = character
            launch {
                ktorClient.getEpisodes(character.episodeIds).onSuccess { episodes ->
                    episodesState = episodes
                }
                    .onFailure {

                    }
            }
        }.onFailure {
            //todo handle this later
        }

    })

    characterState?.let { character ->
        MainScreen(character = character, episodes = episodesState)
    } ?: LoadingState()

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainScreen(character: Character, episodes: List<Episode>) {

    LazyColumn {

        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { CharacterNameComponent(name = character.name) }
        item { Spacer(modifier = Modifier.height(12.dp)) }
        item { CharacterImage(imageUrl = character.image) }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        episodes.groupBy { it.seasonNumber }.forEach { mapEntry ->
            stickyHeader {  SeasonHeader(seasonNumber = mapEntry.key) }
            //header component
            items(mapEntry.value) { episode ->
                EpisodeRowComponent(episode = episode)
            }

        }
    }
}


@Composable
private fun SeasonHeader(seasonNumber: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = RickPrimary)
            .padding(top = 12.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Sezon $seasonNumber",
            color = RickTextPrimary,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = RickTextPrimary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 4.dp)
        )
    }
}