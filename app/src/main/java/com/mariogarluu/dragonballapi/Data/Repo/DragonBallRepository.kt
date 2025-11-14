package com.mariogarluu.dragonballapi.Data.Repo

import com.mariogarluu.dragonballapi.Data.Remote.DragonBallApi
import javax.inject.Inject

/**
 * Repositorio que gestiona los datos de los personajes de Dragon Ball.
 *
 * @param api La API remota para obtener los datos.
 */
class DragonBallRepository @Inject constructor(
    private val api: DragonBallApi
) {
    /**
     * Obtiene la lista completa de personajes.
     *
     * @return Un objeto [CharacterResponse] que contiene la lista de personajes.
     */
    suspend fun getCharacters() = api.getCharacters(100)

    /**
     * Obtiene un personaje específico por su ID.
     *
     * @param id El ID del personaje a obtener.
     * @return Un objeto [Character] con los detalles del personaje.
     */
    suspend fun getCharacterById(id: Int) = api.getCharacterById(id)

    /**
     * Obtiene la lista completa de planetas.
     *
     * @return Un objeto [PlanetResponse] que contiene la lista de planetas.
     */
    suspend fun getPlanets() = api.getPlanets(100)

    /**
     * Obtiene un planeta específico por su ID.
     *
     * @param id El ID del planeta a obtener.
     * @return Un objeto [Planet] con los detalles del planeta.
     */
    suspend fun getPlanetById(id: Int) = api.getPlanetById(id)
}