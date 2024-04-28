package com.muratcangzm.network.models.remote

import com.muratcangzm.network.models.domain.Character
import com.muratcangzm.network.models.domain.CharacterGender
import com.muratcangzm.network.models.domain.CharacterStatus
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val episode: List<String>,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val url: String,
    val created: String
) {

    @Serializable
    data class Origin(
        val name: String,
        val url: String
    )

    @Serializable
    data class Location(
        val name: String,
        val url: String
    )
}

fun RemoteCharacter.toDomainCharacter(): Character {

    val characterGender = when (gender.lowercase()) {
        "male" -> CharacterGender.Male
        "female" -> CharacterGender.Female
        "genderless" -> CharacterGender.Genderless
        else -> CharacterGender.Unknown
    }
    val characterStatus = when (status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }

    return Character(
        id = id,
        name = name,
        status = characterStatus,
        species = species,
        episodeIds = episode.map {it.substring(it.lastIndexOf("/") + 1).toInt()},
        type = type,
        gender = characterGender,
        origin = Character.Origin(name = origin.name, url = origin.url),
        location = Character.Location(name = location.name, url = location.url),
        image = image,
        url = url,
        created = created
    )

}