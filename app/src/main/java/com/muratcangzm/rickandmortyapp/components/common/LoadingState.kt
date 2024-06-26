package com.muratcangzm.rickandmortyapp.components.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muratcangzm.rickandmortyapp.ui.theme.RickAction


private val defaultModifier = Modifier
    .fillMaxSize()
    .padding(all = 18.dp)

@Composable
fun LoadingState(modifier: Modifier = defaultModifier) {

    CircularProgressIndicator(
        modifier = modifier,
        color = RickAction
    )
}

@Preview
@Composable
private fun LoadingStatePreview() {

    LoadingState()
}