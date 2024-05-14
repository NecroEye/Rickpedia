package com.muratcangzm.rickandmortyapp.repository

import com.muratcangzm.network.ApiOperation
import com.muratcangzm.network.KtorClient
import com.muratcangzm.network.models.domain.Character
import javax.inject.Inject

class CharacterRepository
@Inject constructor(private val ktorClient: KtorClient) {

    suspend fun fetchCharacter(characterId:Int): ApiOperation<Character>{
        return ktorClient.getCharacter(characterId)
    }

}