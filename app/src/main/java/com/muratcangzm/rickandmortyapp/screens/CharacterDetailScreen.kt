package com.muratcangzm.rickandmortyapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.muratcangzm.network.KtorClient
import com.muratcangzm.network.models.domain.Character
import com.muratcangzm.rickandmortyapp.components.character.CharacterDetailsNamePlateComponent
import com.muratcangzm.rickandmortyapp.components.common.DataPoint
import com.muratcangzm.rickandmortyapp.components.common.DataPointComponent
import com.muratcangzm.rickandmortyapp.components.common.LoadingState
import com.muratcangzm.rickandmortyapp.ui.theme.RickAction
import com.muratcangzm.rickandmortyapp.viewmodels.CharacterDetailsViewModel


sealed interface CharacterDetailsViewState {
    object Loading : CharacterDetailsViewState
    data class Error(val message: String) : CharacterDetailsViewState
    data class Success(
        val character: Character,
        val characterDataPoint: List<DataPoint>
    ) : CharacterDetailsViewState
}


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    onEpisodeClick: (Int) -> Unit
) {

    val state by viewModel.stateFlow.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchCharacterDetails(characterId)
    })

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp)
    ) {

        when (state) {
            is CharacterDetailsViewState.Loading -> item { LoadingState() }
            is CharacterDetailsViewState.Error -> {
                // TODO:  
            }
            is CharacterDetailsViewState.Success -> {

                val (character, characterDataPoints) = (state as CharacterDetailsViewState.Success)

                //Karakter Durumu
                item {
                    CharacterDetailsNamePlateComponent(
                        name = character.name,
                        status = character.status
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                //Karakter Resim
                item {
                    SubcomposeAsyncImage(
                        model = character.image,
                        contentDescription = "Karakter Resmi",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(shape = RoundedCornerShape(12.dp)),
                        loading = { LoadingState() },

                        )

                }

                //Genel Karakter Bilgileri
                items(characterDataPoints) {
                    Spacer(modifier = Modifier.height(32.dp))
                    DataPointComponent(dataPoint = it)
                }

                item { Spacer(modifier = Modifier.height(32.dp)) }

                //Button
                item {
                    Text(
                        text = "Tüm Bölümleri Görüntüle",
                        color = RickAction,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .border(
                                width = 1.dp,
                                color = RickAction,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(shape = RoundedCornerShape(12.dp))
                            .clickable {
                                onEpisodeClick(character.id)
                            }
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()

                    )
                }

                item { Spacer(modifier = Modifier.height(64.dp)) }


            }
        }
    }
}


