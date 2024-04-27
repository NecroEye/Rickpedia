package com.muratcangzm.network.models.domain

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

sealed class CharacterStatus(val displayName:String, val color: Color) {

    object Alive : CharacterStatus("Alive", Color.Green)
    object Dead : CharacterStatus("Dead", Color.Red)
    object Unknown : CharacterStatus("Unknown", Color.Gray)

}