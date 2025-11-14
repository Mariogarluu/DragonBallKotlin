package com.mariogarluu.dragonballapi.ui

import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.Data.Model.Planet
import com.mariogarluu.dragonballapi.Data.Model.Transformation

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
    /**
     * @param character El personaje completo con todas sus transformaciones.
     * @param currentTransformation La transformación que se está mostrando (null si es la forma base).
     * @param hasNextTransformation Indica si hay una transformación siguiente.
     * @param hasPreviousTransformation Indica si hay una transformación anterior.
     * @param hasNextCharacter Indica si hay un personaje siguiente en la lista.
     * @param hasPreviousCharacter Indica si hay un personaje anterior en la lista.
     */
    data class Success(
        val character: Character,
        val currentTransformation: Transformation? = null,
        val hasNextTransformation: Boolean,
        val hasPreviousTransformation: Boolean,
        val hasNextCharacter: Boolean,
        val hasPreviousCharacter: Boolean
    ) : CharacterUiState()
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
