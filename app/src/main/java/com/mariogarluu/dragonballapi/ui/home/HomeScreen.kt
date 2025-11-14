package com.mariogarluu.dragonballapi.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable que representa la pantalla de inicio de la aplicación.
 *
 * @param onCharactersClick Función de callback que se invoca al hacer clic en el botón de personajes.
 * @param onPlanetsClick Función de callback que se invoca al hacer clic en el botón de planetas.
 */
@Composable
fun HomeScreen(
    onCharactersClick: () -> Unit,
    onPlanetsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onCharactersClick) {
            Text(text = "Personajes")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onPlanetsClick) {
            Text(text = "Planetas")
        }
    }
}