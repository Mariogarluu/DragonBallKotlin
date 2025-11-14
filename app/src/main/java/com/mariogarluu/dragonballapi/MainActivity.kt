package com.mariogarluu.dragonballapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mariogarluu.dragonballapi.ui.navegation.NavGraph
import com.mariogarluu.dragonballapi.ui.theme.DragonBallApiTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * La actividad principal de la aplicación.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Se llama cuando se crea la actividad. Configura el contenido de la interfaz de usuario.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonBallApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavGraph(navController = navController, contentPadding = innerPadding)
                }
            }
        }
    }
}
