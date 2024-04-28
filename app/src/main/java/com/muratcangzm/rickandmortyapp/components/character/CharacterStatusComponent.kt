package com.muratcangzm.rickandmortyapp.components.character

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratcangzm.network.models.domain.CharacterStatus
import com.muratcangzm.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.muratcangzm.rickandmortyapp.ui.theme.RickTextPrimary


@Composable
fun CharacterStatusComponent(status: CharacterStatus) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = status.color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {

        Text(
            text = status.displayName ,
            fontSize = 20.sp,
            color = RickTextPrimary
        )
    }
}

@Preview
@Composable
private fun CharacterStatusComponentPreviewAlive() {
    RickAndMortyAppTheme {
        CharacterStatusComponent(status = CharacterStatus.Alive)
    }
}

@Preview
@Composable
private fun CharacterStatusComponentPreviewDead() {
    RickAndMortyAppTheme {
        CharacterStatusComponent(status = CharacterStatus.Dead)
    }
}


@Preview
@Composable
private fun CharacterStatusComponentPreviewUnknown() {
    RickAndMortyAppTheme {
        CharacterStatusComponent(status = CharacterStatus.Unknown)
    }
}




