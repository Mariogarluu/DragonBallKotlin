package com.mariogarluu.dragonballapi.Data.Model

/**
 * Representa la respuesta completa de la API al solicitar la lista de personajes.
 */
data class CharacterResponse(
    val items: List<Character>,
    val meta: Meta
)

/**
 * Representa un único personaje de Dragon Ball, incluyendo sus transformaciones.
 */
data class Character(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val originPlanet: Planet?,
    val transformations: List<Transformation>
)

/**
 * Representa una de las transformaciones de un personaje.
 */
data class Transformation(
    val id: Int,
    val name: String,
    val image: String,
    val ki: String
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
    val characters: List<Character>? = null // Puede ser nulo
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
