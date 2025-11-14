package com.mariogarluu.dragonballapi.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.Data.Repo.DragonBallRepository
import com.mariogarluu.dragonballapi.ui.CharacterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de detalle del personaje.
 *
 * @param repository El repositorio para obtener los datos del personaje.
 * @param savedStateHandle Manejador del estado guardado para obtener los argumentos de navegación.
 */
@HiltViewModel
class DragonBallDetailViewModel @Inject constructor(
    private val repository: DragonBallRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private var character: Character? = null
    private var currentTransformationIndex = -1
    private var currentCharacterId: Int = savedStateHandle["characterId"]!!

    init {
        getCharacterById(currentCharacterId)
    }

    private fun getCharacterById(id: Int) {
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading
            try {
                val char = repository.getCharacterById(id)
                character = char
                currentCharacterId = id
                currentTransformationIndex = -1
                updateState(char)
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error("No se pudo cargar el personaje.")
            }
        }
    }

    fun nextTransformation() {
        character?.let { char ->
            if (currentTransformationIndex < char.transformations.size - 1) {
                currentTransformationIndex++
                updateState(char)
            }
        }
    }

    fun previousTransformation() {
        character?.let { char ->
            if (currentTransformationIndex >= 0) {
                currentTransformationIndex--
                updateState(char)
            }
        }
    }

    fun nextCharacter() {
        if (currentCharacterId < 58) { // Hay 58 personajes en la API
            getCharacterById(currentCharacterId + 1)
        }
    }

    fun previousCharacter() {
        if (currentCharacterId > 1) {
            getCharacterById(currentCharacterId - 1)
        }
    }

    private fun updateState(char: Character) {
        val transformation = if (currentTransformationIndex != -1) char.transformations[currentTransformationIndex] else null
        _uiState.value = CharacterUiState.Success(
            character = char,
            currentTransformation = transformation,
            hasNextTransformation = currentTransformationIndex < char.transformations.size - 1,
            hasPreviousTransformation = currentTransformationIndex > -1,
            hasNextCharacter = char.id < 58,
            hasPreviousCharacter = char.id > 1
        )
    }
}