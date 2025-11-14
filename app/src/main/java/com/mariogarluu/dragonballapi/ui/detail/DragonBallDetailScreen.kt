package com.mariogarluu.dragonballapi.ui.detail

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
import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.ui.CharacterUiState
import com.mariogarluu.dragonballapi.ui.common.AppTopBar

/**
 * Composable que representa la pantalla de detalle de un personaje.
 *
 * @param onUpClick Función de callback que se invoca al hacer clic en el botón de retroceso.
 * @param viewModel ViewModel para esta pantalla.
 */
@Composable
fun DragonBallDetailScreen(
    onUpClick: () -> Unit,
    viewModel: DragonBallDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Detalle del Personaje",
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
                is CharacterUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is CharacterUiState.Success -> {
                    CharacterDetailContent(character = state.character)
                }
                is CharacterUiState.Error -> {
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
 * Composable que muestra el contenido de la pantalla de detalle del personaje.
 *
 * @param character El personaje a mostrar.
 */
@Composable
private fun CharacterDetailContent(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .crossfade(true)
                .build(),
            contentDescription = character.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Raza: ${character.race}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ki: ${character.ki}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Universo: ${character.universe}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.description, style = MaterialTheme.typography.bodyMedium)
    }
}