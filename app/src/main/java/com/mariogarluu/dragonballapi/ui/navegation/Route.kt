package com.mariogarluu.dragonballapi.ui.navegation

/**
 * Define las rutas de navegación de la aplicación de forma segura.
 */
sealed class Route(val route: String) {
    /**
     * Ruta para la pantalla de inicio.
     */
    object Home : Route("home")

    /**
     * Ruta para la pantalla de la lista de personajes.
     */
    object CharacterList : Route("characters")

    /**
     * Ruta para la pantalla de detalle del personaje. Requiere un ID de personaje.
     */
    object CharacterDetail : Route("character/{characterId}") {
        /**
         * Crea la ruta de navegación para el detalle de un personaje específico.
         * @param characterId El ID del personaje.
         * @return La ruta de navegación completa.
         */
        fun createRoute(characterId: Int) = "character/$characterId"
    }

    /**
     * Ruta para la pantalla de la lista de planetas.
     */
    object PlanetList : Route("planets")

    /**
     * Ruta para la pantalla de detalle del planeta. Requiere un ID de planeta.
     */
    object PlanetDetail : Route("planet/{planetId}") {
        /**
         * Crea la ruta de navegación para el detalle de un planeta específico.
         * @param planetId El ID del planeta.
         * @return La ruta de navegación completa.
         */
        fun createRoute(planetId: Int) = "planet/$planetId"
    }
}