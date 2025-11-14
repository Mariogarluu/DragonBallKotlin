package com.mariogarluu.dragonballapi.ui.planets.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.mariogarluu.dragonballapi.ui.PlanetsUiState
import com.mariogarluu.dragonballapi.ui.common.AppTopBar

/**
 * Composable que representa la pantalla principal de la lista de planetas.
 *
 * @param onPlanetClick Función de callback que se invoca al hacer clic en un planeta.
 * @param onUpClick Función de callback que se invoca al hacer clic en el botón de retroceso.
 * @param viewModel ViewModel para esta pantalla.
 */
@Composable
fun PlanetListScreen(
    onPlanetClick: (Int) -> Unit,
    onUpClick: () -> Unit,
    viewModel: PlanetListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Planetas",
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
                is PlanetsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PlanetsUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
                    ) {
                        items(state.planets) { planet ->
                            PlanetItem(
                                planet = planet,
                                onPlanetClick = { onPlanetClick(planet.id) }
                            )
                        }
                    }
                }
                is PlanetsUiState.Error -> {
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
 * Composable que representa un único elemento de la lista de planetas.
 *
 * @param planet El planeta a mostrar.
 * @param onPlanetClick Función de callback que se invoca al hacer clic en el elemento.
 */
@Composable
fun PlanetItem(
    planet: Planet,
    onPlanetClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onPlanetClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(planet.image)
                    .crossfade(true)
                    .build(),
                contentDescription = planet.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = planet.name)
        }
    }
}