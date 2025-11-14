package com.mariogarluu.dragonballapi.ui.navegation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mariogarluu.dragonballapi.ui.detail.DragonBallDetailScreen
import com.mariogarluu.dragonballapi.ui.home.HomeScreen
import com.mariogarluu.dragonballapi.ui.list.DragonBallListScreen
import com.mariogarluu.dragonballapi.ui.planets.detail.PlanetDetailScreen
import com.mariogarluu.dragonballapi.ui.planets.list.PlanetListScreen

/**
 * Define el gráfico de navegación de la aplicación.
 *
 * @param navController El controlador de navegación.
 * @param contentPadding El relleno de contenido para las pantallas.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(
                onCharactersClick = { navController.navigate(Route.CharacterList.route) },
                onPlanetsClick = { navController.navigate(Route.PlanetList.route) }
            )
        }
        composable(Route.CharacterList.route) {
            DragonBallListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(Route.CharacterDetail.createRoute(characterId))
                },
                onUpClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Route.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            DragonBallDetailScreen(
                onUpClick = { navController.popBackStack() }
            )
        }
        composable(Route.PlanetList.route) {
            PlanetListScreen(
                onPlanetClick = { planetId ->
                    navController.navigate(Route.PlanetDetail.createRoute(planetId))
                },
                onUpClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Route.PlanetDetail.route,
            arguments = listOf(navArgument("planetId") { type = NavType.IntType })
        ) {
            PlanetDetailScreen(
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}