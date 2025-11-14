package com.mariogarluu.dragonballapi.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private val characterId: Int = savedStateHandle["characterId"]!!

    init {
        getCharacterById()
    }

    /**
     * Obtiene los detalles del personaje del repositorio y actualiza el estado de la interfaz de usuario.
     */
    private fun getCharacterById() {
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading
            try {
                _uiState.value = CharacterUiState.Success(repository.getCharacterById(characterId))
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}