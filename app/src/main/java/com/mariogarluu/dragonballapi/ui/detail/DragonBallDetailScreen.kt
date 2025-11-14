package com.mariogarluu.dragonballapi.ui.detail

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mariogarluu.dragonballapi.Data.Model.Character
import com.mariogarluu.dragonballapi.Data.Model.Transformation
import com.mariogarluu.dragonballapi.ui.CharacterUiState
import com.mariogarluu.dragonballapi.ui.common.AppTopBar
import kotlin.math.abs

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
                    CharacterDetailContent(
                        character = state.character,
                        currentTransformation = state.currentTransformation,
                        hasNextTransformation = state.hasNextTransformation,
                        hasPreviousTransformation = state.hasPreviousTransformation,
                        onNextTransformation = { viewModel.nextTransformation() },
                        onPreviousTransformation = { viewModel.previousTransformation() },
                        hasNextCharacter = state.hasNextCharacter,
                        hasPreviousCharacter = state.hasPreviousCharacter,
                        onNextCharacter = { viewModel.nextCharacter() },
                        onPreviousCharacter = { viewModel.previousCharacter() }
                    )
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

@Composable
private fun CharacterDetailContent(
    character: Character,
    currentTransformation: Transformation?,
    hasNextTransformation: Boolean,
    hasPreviousTransformation: Boolean,
    onNextTransformation: () -> Unit,
    onPreviousTransformation: () -> Unit,
    hasNextCharacter: Boolean,
    hasPreviousCharacter: Boolean,
    onNextCharacter: () -> Unit,
    onPreviousCharacter: () -> Unit
) {
    val name = currentTransformation?.name ?: character.name
    val image = currentTransformation?.image ?: character.image
    val ki = currentTransformation?.ki ?: character.ki

    var horizontalDrag by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(character.id) { // Clave única para reiniciar el gesto
                detectDragGestures(
                    onDragStart = { horizontalDrag = 0f },
                    onDragEnd = {
                        val swipeThreshold = 150.dp.toPx()
                        when {
                            horizontalDrag > swipeThreshold && hasPreviousCharacter -> onPreviousCharacter()
                            horizontalDrag < -swipeThreshold && hasNextCharacter -> onNextCharacter()
                        }
                    },
                    onDrag = { change, dragAmount ->
                        if (abs(dragAmount.x) > abs(dragAmount.y)) {
                            change.consume()
                            horizontalDrag += dragAmount.x
                        } else {
                            // Es un gesto vertical, no hacemos nada para que el scroll funcione
                        }
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ki: $ki", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Raza: ${character.race}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        character.originPlanet?.let {
            Text(text = "Planeta de Origen: ${it.name}", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))
        TransformationButtons(
            onNext = onNextTransformation,
            onPrevious = onPreviousTransformation,
            hasNext = hasNextTransformation,
            hasPrevious = hasPreviousTransformation
        )
    }
}

@Composable
private fun TransformationButtons(
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    hasNext: Boolean,
    hasPrevious: Boolean
) {
    if (hasNext || hasPrevious) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasPrevious) {
                IconButton(onClick = onPrevious) { Icon(Icons.Default.ArrowBack, contentDescription = "Anterior") }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
            Text(text = "Transformaciones")
            if (hasNext) {
                IconButton(onClick = onNext) { Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente") }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        }
    }
}