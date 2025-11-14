package com.mariogarluu.dragonballapi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariogarluu.dragonballapi.Data.Repo.DragonBallRepository
import com.mariogarluu.dragonballapi.ui.CharactersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de la lista de personajes.
 *
 * @param repository El repositorio para obtener los datos de los personajes.
 */
@HiltViewModel
class DragonBallListViewModel @Inject constructor(
    private val repository: DragonBallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    /**
     * Obtiene la lista de personajes del repositorio y actualiza el estado de la interfaz de usuario.
     */
    private fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = CharactersUiState.Loading
            try {
                val response = repository.getCharacters()
                _uiState.value = CharactersUiState.Success(response.items)
            } catch (e: Exception) {
                _uiState.value = CharactersUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}