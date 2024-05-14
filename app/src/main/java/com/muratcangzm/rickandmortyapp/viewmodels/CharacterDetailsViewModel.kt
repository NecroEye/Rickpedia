package com.muratcangzm.rickandmortyapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratcangzm.network.ApiOperation
import com.muratcangzm.network.models.domain.Character
import com.muratcangzm.rickandmortyapp.components.common.DataPoint
import com.muratcangzm.rickandmortyapp.repository.CharacterRepository
import com.muratcangzm.rickandmortyapp.screens.CharacterDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel
@Inject
constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _internalStorageFlow = MutableStateFlow<CharacterDetailsViewState>(
        value = CharacterDetailsViewState.Loading
    )
    val stateFlow: StateFlow<CharacterDetailsViewState>
        get() = _internalStorageFlow.asStateFlow()

    init {

    }

    fun fetchCharacterDetails(characterId: Int) = viewModelScope.launch {
        _internalStorageFlow.update { CharacterDetailsViewState.Loading }
        characterRepository.fetchCharacter(characterId)
            .onSuccess { character ->
                val dataPoint = buildList {
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

                _internalStorageFlow.update {
                    return@update CharacterDetailsViewState.Success(
                        character = character,
                        characterDataPoint = dataPoint
                    )
                }

            }.onFailure { exception ->
                _internalStorageFlow.update {
                    return@update CharacterDetailsViewState.Error(
                        message = exception.message ?: "Bilinmeyen Hata"
                    )
                }
            }

    }
}