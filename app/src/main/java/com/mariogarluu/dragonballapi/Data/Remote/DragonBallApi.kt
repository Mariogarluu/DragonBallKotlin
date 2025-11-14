package com.mariogarluu.dragonballapi.Data.Remote

import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.Data.Model.CharacterResponse
import com.mariogarluu.dragonballapi.Data.Model.Planet
import com.mariogarluu.dragonballapi.Data.Model.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define los endpoints de la API de Dragon Ball utilizando Retrofit.
 */
interface DragonBallApi {
    /**
     * Obtiene la lista completa de personajes.
     *
     * @param limit El número máximo de personajes a devolver.
     * @return Un objeto [CharacterResponse] que contiene la lista de personajes y metadatos.
     */
    @GET("characters")
    suspend fun getCharacters(@Query("limit") limit: Int): CharacterResponse

    /**
     * Obtiene un personaje específico por su ID.
     *
     * @param id El ID del personaje a obtener.
     * @return Un objeto [Character] con los detalles del personaje.
     */
    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    /**
     * Obtiene la lista completa de planetas.
     *
     * @param limit El número máximo de planetas a devolver.
     * @return Un objeto [PlanetResponse] que contiene la lista de planetas y metadatos.
     */
    @GET("planets")
    suspend fun getPlanets(@Query("limit") limit: Int): PlanetResponse

    /**
     * Obtiene un planeta específico por su ID.
     *
     * @param id El ID del planeta a obtener.
     * @return Un objeto [Planet] con los detalles del planeta.
     */
    @GET("planets/{id}")
    suspend fun getPlanetById(@Path("id") id: Int): Planet
}