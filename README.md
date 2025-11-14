# 🐉 DragonBall API - Aplicación Android

Una aplicación Android moderna desarrollada con Kotlin y Jetpack Compose que consume la API pública de Dragon Ball para mostrar información detallada sobre personajes y planetas del universo Dragon Ball.

## 📱 Descripción

DragonBall API es una aplicación nativa de Android que permite a los usuarios explorar el vasto universo de Dragon Ball. La aplicación proporciona una interfaz intuitiva y moderna para navegar entre personajes icónicos y planetas, mostrando información detallada como transformaciones, niveles de Ki, razas, afiliaciones y más.

## ✨ Características Principales

### 🎭 Personajes
- **Lista completa de personajes**: Visualiza todos los personajes de Dragon Ball en una lista scrolleable
- **Detalles del personaje**: Información detallada incluyendo:
  - Imagen del personaje
  - Nombre
  - Nivel de Ki y Ki máximo
  - Raza
  - Género
  - Planeta de origen
  - Afiliación
  - Descripción completa
- **Transformaciones**: Navega entre las diferentes transformaciones de cada personaje con botones intuitivos
- **Navegación por gestos**: Desliza horizontalmente para moverte entre personajes

### 🪐 Planetas
- **Lista de planetas**: Explora todos los planetas del universo Dragon Ball
- **Información detallada**: Cada planeta incluye:
  - Imagen representativa
  - Nombre
  - Estado (destruido o no)
  - Descripción
  - Personajes originarios del planeta

### 🎨 Interfaz de Usuario
- Diseño moderno con Material Design 3
- Animaciones fluidas y transiciones suaves
- Temas adaptativos que respetan las preferencias del sistema
- Carga de imágenes optimizada con caché
- Estados de carga y error bien manejados

## 🏗️ Arquitectura y Stack Tecnológico

La aplicación sigue las mejores prácticas de desarrollo Android moderno:

### Lenguaje y Frameworks
- **Kotlin**: Lenguaje principal de desarrollo
- **Jetpack Compose**: Framework moderno para construir UI declarativas
- **Material Design 3**: Sistema de diseño para una UI consistente

### Arquitectura
- **MVVM (Model-View-ViewModel)**: Patrón arquitectónico para separación de responsabilidades
- **Repository Pattern**: Abstracción de las fuentes de datos
- **Clean Architecture**: Separación en capas (Data, Domain, UI)

### Inyección de Dependencias
- **Dagger Hilt**: Framework para inyección de dependencias simplificada en Android

### Networking
- **Retrofit**: Cliente HTTP type-safe para Android
- **Gson**: Serialización/deserialización JSON
- **Coroutines**: Manejo asíncrono de operaciones de red

### Gestión de Estado
- **StateFlow**: Para gestión reactiva de estados en ViewModels
- **Compose State**: Gestión de estado en componentes UI

### Navegación
- **Navigation Compose**: Sistema de navegación para Jetpack Compose

### Carga de Imágenes
- **Coil**: Librería moderna para carga de imágenes optimizada para Compose

## 📁 Estructura del Proyecto

```
app/src/main/java/com/mariogarluu/dragonballapi/
├── Data/                          # Capa de datos
│   ├── Model/                     # Modelos de datos
│   │   └── Model.kt              # Character, Planet, Transformation, etc.
│   ├── Remote/                    # Fuentes de datos remotas
│   │   └── DragonBallApi.kt      # Interfaz de la API con Retrofit
│   └── Repo/                      # Repositorios
│       └── DragonBallRepository.kt
├── di/                            # Inyección de dependencias
│   ├── DragonBallAplication.kt   # Configuración de Hilt
│   └── RemoteModule.kt           # Módulo de red
├── ui/                            # Capa de presentación
│   ├── common/                    # Componentes UI reutilizables
│   │   └── AppTopBar.kt          # Barra superior
│   ├── detail/                    # Detalle de personaje
│   │   ├── DragonBallDetailScreen.kt
│   │   └── DragonBallDetailViewModel.kt
│   ├── home/                      # Pantalla principal
│   │   └── HomeScreen.kt
│   ├── list/                      # Lista de personajes
│   │   ├── DragonBallListScreen.kt
│   │   └── DragonBallListViewModel.kt
│   ├── planets/                   # Sección de planetas
│   │   ├── detail/
│   │   │   ├── PlanetDetailScreen.kt
│   │   │   └── PlanetDetailViewModel.kt
│   │   └── list/
│   │       ├── PlanetListScreen.kt
│   │       └── PlanetListViewModel.kt
│   ├── navegation/                # Sistema de navegación
│   │   ├── NavGraph.kt           # Gráfico de navegación
│   │   └── Route.kt              # Rutas de navegación
│   ├── theme/                     # Tema de la aplicación
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── UiState.kt                # Estados de UI
├── DragonBallApiApp.kt           # Aplicación principal
└── MainActivity.kt               # Actividad principal
```

## 🚀 Instalación y Configuración

### Requisitos Previos
- **Android Studio**: Hedgehog (2023.1.1) o superior
- **JDK**: Java 11 o superior
- **SDK de Android**: API Level 34 (Android 14) o superior
- **Gradle**: 8.13.1 (se incluye con el proyecto)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Mariogarluu/DragonBallKotlin.git
   cd DragonBallKotlin
   ```

2. **Abrir en Android Studio**
   - Abre Android Studio
   - Selecciona "Open an Existing Project"
   - Navega hasta la carpeta del proyecto clonado
   - Espera a que Gradle sincronice las dependencias

3. **Configurar el dispositivo**
   - **Emulador**: Crea un AVD con API 34 o superior
   - **Dispositivo físico**: Activa las opciones de desarrollador y la depuración USB

4. **Ejecutar la aplicación**
   - Presiona el botón "Run" (▶️) o usa `Shift + F10`
   - Selecciona tu dispositivo/emulador
   - Espera a que la aplicación se compile e instale

## 🔧 Compilación

### Compilar desde la línea de comandos

```bash
# Compilar versión debug
./gradlew assembleDebug

# Compilar versión release
./gradlew assembleRelease

# Ejecutar tests
./gradlew test

# Instalar en dispositivo conectado
./gradlew installDebug
```

### Archivos generados
Los APKs compilados se encuentran en:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

## 🌐 API Utilizada

La aplicación consume la [Dragon Ball API](https://dragonball-api.com/api/):
- **Base URL**: `https://dragonball-api.com/api/`
- **Endpoints utilizados**:
  - `GET /characters`: Lista de todos los personajes
  - `GET /characters/{id}`: Detalle de un personaje específico
  - `GET /planets`: Lista de todos los planetas
  - `GET /planets/{id}`: Detalle de un planeta específico

### Límites de la API
- Sin necesidad de autenticación
- Sin límites de rate conocidos
- Respuestas en formato JSON

## 📖 Uso de la Aplicación

### Navegación Principal

1. **Pantalla de Inicio**
   - Al abrir la aplicación, verás dos botones:
     - "Personajes": Navega a la lista de personajes
     - "Planetas": Navega a la lista de planetas

2. **Lista de Personajes**
   - Muestra tarjetas con imagen y nombre de cada personaje
   - Toca cualquier personaje para ver sus detalles
   - Usa el botón de retroceso (←) para volver al inicio

3. **Detalle de Personaje**
   - Visualiza toda la información del personaje
   - Si tiene transformaciones, usa los botones ← y → para navegar entre ellas
   - Desliza horizontalmente para cambiar de personaje
   - Scroll vertical para ver toda la información

4. **Lista de Planetas**
   - Similar a la lista de personajes
   - Muestra imagen y nombre de cada planeta
   - Toca para ver detalles

5. **Detalle de Planeta**
   - Información completa del planeta
   - Estado de destrucción
   - Personajes asociados al planeta

## 🛠️ Tecnologías y Librerías

| Tecnología | Versión | Propósito |
|-----------|---------|-----------|
| Kotlin | 2.0.21 | Lenguaje de programación |
| Android Gradle Plugin | 8.13.1 | Sistema de compilación |
| Jetpack Compose | 2024.09.00 | Framework de UI |
| Compose Material3 | - | Componentes Material Design 3 |
| Dagger Hilt | 2.51.1 | Inyección de dependencias |
| Retrofit | 2.9.0 | Cliente HTTP |
| Gson | 2.9.0 | Serialización JSON |
| Coil | 2.5.0 | Carga de imágenes |
| Navigation Compose | 2.8.0-beta05 | Navegación |
| Lifecycle ViewModel | 2.9.4 | Gestión de ciclo de vida |

## 🎯 Características Técnicas Destacadas

### Gestión de Estados
- Estados bien definidos: `Loading`, `Success`, `Error`
- UI reactiva que responde automáticamente a cambios de estado
- Manejo robusto de errores de red

### Optimizaciones
- Caché de imágenes con Coil
- Carga lazy de listas (LazyColumn)
- Coroutines para operaciones asíncronas
- Singleton de Retrofit para reutilización de conexiones

### UX/UI
- Indicadores de carga durante peticiones de red
- Mensajes de error informativos
- Transiciones suaves entre pantallas
- Soporte para modo claro/oscuro (automático según sistema)
- Gestos táctiles para navegación mejorada

## 🔐 Permisos

La aplicación requiere el siguiente permiso:
- `INTERNET`: Para realizar peticiones HTTP a la API de Dragon Ball

## 📱 Requisitos del Sistema

- **Android mínimo**: API 34 (Android 14)
- **Android objetivo**: API 36
- **Arquitectura**: arm64-v8a, armeabi-v7a, x86, x86_64

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo licencia libre para uso educativo.

## 👨‍💻 Autor

**Mario Garluu** - [GitHub](https://github.com/Mariogarluu)

## 🙏 Agradecimientos

- [Dragon Ball API](https://dragonball-api.com/) por proporcionar la API pública
- La comunidad de Android y Jetpack Compose por los recursos y documentación
- Akira Toriyama por crear el universo de Dragon Ball

## 📞 Contacto y Soporte

Para preguntas, sugerencias o reportar problemas:
- Abre un [Issue](https://github.com/Mariogarluu/DragonBallKotlin/issues) en GitHub
- Contacta al autor a través de su perfil de GitHub

---

**¡Disfruta explorando el universo de Dragon Ball!** 🐲⭐
