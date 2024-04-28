package com.muratcangzm.network.models.domain


sealed class CharacterGender(val displayName:String) {

    object Male: CharacterGender("Erkek")
    object Female: CharacterGender("Kadın")
    object Genderless: CharacterGender("Cinsiyetsiz")
    object Unknown: CharacterGender("Belirtilmemiş")

}