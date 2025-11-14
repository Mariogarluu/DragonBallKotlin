package com.mariogarluu.dragonballapi.ui

import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.Data.Model.Planet

/**
 * Representa los posibles estados de la interfaz de usuario para la pantalla de la lista de personajes.
 */
sealed class CharactersUiState {
    object Loading : CharactersUiState()
    data class Success(val characters: List<Character>) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}

/**
 * Representa los posibles estados de la interfaz de usuario para la pantalla de detalle del personaje.
 */
sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val character: Character) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}

/**
 * Representa los posibles estados de la interfaz de usuario para la pantalla de la lista de planetas.
 */
sealed class PlanetsUiState {
    object Loading : PlanetsUiState()
    data class Success(val planets: List<Planet>) : PlanetsUiState()
    data class Error(val message: String) : PlanetsUiState()
}

/**
 * Representa los posibles estados de la interfaz de usuario para la pantalla de detalle del planeta.
 */
sealed class PlanetUiState {
    object Loading : PlanetUiState()
    data class Success(val planet: Planet) : PlanetUiState()
    data class Error(val message: String) : PlanetUiState()
}
