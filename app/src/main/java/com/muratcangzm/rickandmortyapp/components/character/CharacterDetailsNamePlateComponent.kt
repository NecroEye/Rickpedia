package com.muratcangzm.rickandmortyapp.components.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muratcangzm.network.models.domain.CharacterStatus
import com.muratcangzm.rickandmortyapp.components.common.CharacterNameComponent


@Composable
fun CharacterDetailsNamePlateComponent(name:String, status: CharacterStatus){
    Column(modifier = Modifier.fillMaxWidth()) {

        CharacterStatusComponent(status = status)
        CharacterNameComponent(name = name)

    }
}


@Preview
@Composable
private fun CharacterDetailsNamePlateComponentPreviewAlive(){
    CharacterDetailsNamePlateComponent(name = "Murat", status = CharacterStatus.Alive)
}

@Preview
@Composable
private fun CharacterDetailsNamePlateComponentPreviewDead(){
    CharacterDetailsNamePlateComponent(name = "Murat", status = CharacterStatus.Dead)
}

@Preview
@Composable
private fun CharacterDetailsNamePlateComponentPreviewUnknown(){
    CharacterDetailsNamePlateComponent(name = "Murat", status = CharacterStatus.Unknown)
}