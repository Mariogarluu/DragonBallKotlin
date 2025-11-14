package com.mariogarluu.dragonballapi.ui.planets.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mariogarluu.dragonballapi.Data.Model.Planet
import com.mariogarluu.dragonballapi.ui.PlanetUiState
import com.mariogarluu.dragonballapi.ui.common.AppTopBar

/**
 * Composable que representa la pantalla de detalle de un planeta.
 *
 * @param onUpClick Función de callback que se invoca al hacer clic en el botón de retroceso.
 * @param viewModel ViewModel para esta pantalla.
 */
@Composable
fun PlanetDetailScreen(
    onUpClick: () -> Unit,
    viewModel: PlanetDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Detalle del Planeta",
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = onUpClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState) {
                is PlanetUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PlanetUiState.Success -> {
                    PlanetDetailContent(planet = state.planet)
                }
                is PlanetUiState.Error -> {
                    Text(
                        text = state.message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

/**
 * Composable que muestra el contenido de la pantalla de detalle del planeta.
 *
 * @param planet El planeta a mostrar.
 */
@Composable
private fun PlanetDetailContent(planet: Planet) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(planet.image)
                .crossfade(true)
                .build(),
            contentDescription = planet.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = planet.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = if (planet.isDestroyed) "Destruido" else "No destruido", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = planet.description, style = MaterialTheme.typography.bodyMedium)
    }
}