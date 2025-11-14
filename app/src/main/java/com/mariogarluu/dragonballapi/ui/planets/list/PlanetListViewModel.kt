package com.mariogarluu.dragonballapi.ui.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariogarluu.dragonballapi.Data.Repo.DragonBallRepository
import com.mariogarluu.dragonballapi.ui.PlanetsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de la lista de planetas.
 *
 * @param repository El repositorio para obtener los datos de los planetas.
 */
@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val repository: DragonBallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlanetsUiState>(PlanetsUiState.Loading)
    val uiState: StateFlow<PlanetsUiState> = _uiState.asStateFlow()

    init {
        getPlanets()
    }

    /**
     * Obtiene la lista de planetas del repositorio y actualiza el estado de la interfaz de usuario.
     */
    private fun getPlanets() {
        viewModelScope.launch {
            _uiState.value = PlanetsUiState.Loading
            try {
                val response = repository.getPlanets()
                _uiState.value = PlanetsUiState.Success(response.items)
            } catch (e: Exception) {
                _uiState.value = PlanetsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}