package com.muratcangzm.network.models.domain

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

sealed class CharacterStatus(val displayName:String, val color: Color) {

    object Alive : CharacterStatus("Canlı", Color.Green)
    object Dead : CharacterStatus("Ölü", Color.Red)
    object Unknown : CharacterStatus("Bilinmiyor", Color.Gray)

}