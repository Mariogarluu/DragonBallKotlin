package com.mariogarluu.dragonballapi.ui.planets.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariogarluu.dragonballapi.Data.Repo.DragonBallRepository
import com.mariogarluu.dragonballapi.ui.PlanetUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de detalle del planeta.
 *
 * @param repository El repositorio para obtener los datos del planeta.
 * @param savedStateHandle Manejador del estado guardado para obtener los argumentos de navegación.
 */
@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val repository: DragonBallRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlanetUiState>(PlanetUiState.Loading)
    val uiState: StateFlow<PlanetUiState> = _uiState.asStateFlow()

    private val planetId: Int = savedStateHandle["planetId"]!!

    init {
        getPlanetById()
    }

    /**
     * Obtiene los detalles del planeta del repositorio y actualiza el estado de la interfaz de usuario.
     */
    private fun getPlanetById() {
        viewModelScope.launch {
            _uiState.value = PlanetUiState.Loading
            try {
                _uiState.value = PlanetUiState.Success(repository.getPlanetById(planetId))
            } catch (e: Exception) {
                _uiState.value = PlanetUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}