package com.mariogarluu.dragonballapi.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Composable reutilizable para la barra de aplicación superior.
 *
 * @param title El título a mostrar en la barra de aplicación.
 * @param imageVector El icono a mostrar en el botón de navegación.
 * @param onClick La acción a realizar al hacer clic en el botón de navegación.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, imageVector: ImageVector, onClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(imageVector = imageVector, contentDescription = null)
            }
        }
    )
}