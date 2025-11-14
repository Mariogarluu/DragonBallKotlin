package com.mariogarluu.dragonballapi.Data.Model

/**
 * Representa la respuesta completa de la API al solicitar la lista de personajes.
 */
data class CharacterResponse(
    val items: List<Character>,
    val meta: Meta
)

/**
 * Representa un único personaje de Dragon Ball.
 */
data class Character(
    val id: Int,
    val name: String,
    val race: String,
    val description: String,
    val image: String,
    val ki: String,
    val universe: Int,
    val isTransformed: Boolean
)

/**
 * Representa la respuesta completa de la API al solicitar la lista de planetas.
 */
data class PlanetResponse(
    val items: List<Planet>,
    val meta: Meta
)

/**
 * Representa un único planeta de Dragon Ball.
 */
data class Planet(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
    val characters: List<Character>
)

/**
 * Contiene los metadatos de la paginación de la API.
 */
data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)
