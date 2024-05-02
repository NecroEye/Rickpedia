package com.muratcangzm.rickandmortyapp.screens

import android.util.Log
import android.widget.Space
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.muratcangzm.network.ApiOperation
import com.muratcangzm.network.KtorClient
import com.muratcangzm.network.models.domain.Character
import com.muratcangzm.rickandmortyapp.components.character.CharacterDetailsNamePlateComponent
import com.muratcangzm.rickandmortyapp.components.common.DataPoint
import com.muratcangzm.rickandmortyapp.components.common.DataPointComponent
import com.muratcangzm.rickandmortyapp.components.common.LoadingState
import com.muratcangzm.rickandmortyapp.ui.theme.RickAction
import kotlinx.coroutines.delay


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    ktorClient: KtorClient,
    onEpisodeClick: (Int) -> Unit
) {

    var character by remember { mutableStateOf<Character?>(null) }

    val characterDataPoints: List<DataPoint> by remember {
        derivedStateOf {
            buildList {
                character?.let { character ->
                    add(
                        DataPoint(
                            title = "Son Bulunduğu Konum",
                            description = character.location.name
                        )
                    )
                    add(DataPoint(title = "Türü", description = character.species))
                    add(DataPoint(title = "Cinsiyeti", description = character.gender.displayName))
                    character.type.takeIf { it.isNotEmpty() }?.let { type ->
                        add(DataPoint(title = "Cinsi", description = type))
                    }
                    add(DataPoint(title = "Doğduğu Yer", description = character.origin.name))
                    add(
                        DataPoint(
                            title = "Bölüm Sayısı",
                            description = character.episodeIds.size.toString()
                        )
                    )

                }
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        ktorClient
            .getCharacter(characterId)
            .onSuccess {

                character = it

            }
            .onFailure { exception ->
              Log.d("karakterler", exception.message ?: "")

            }

        Log.d("karakterler", character!!.name)

    })

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp)
    ) {

        if (character == null) {
            item { LoadingState() }
            return@LazyColumn
        }

        //Karakter Durumu
        item {
            CharacterDetailsNamePlateComponent(
                name = character!!.name,
                status = character!!.status
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        //Karakter Resim
        item {
            SubcomposeAsyncImage(
                model = character!!.image,
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
                        onEpisodeClick(character!!.id)
                    }
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(64.dp)) }


    }
}


