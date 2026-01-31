#!/bin/bash

# ========================================================
# ANDROID JETPACK COMPOSE SCAFFOLDING (CLEAN ARCHITECTURE)
# ========================================================
#
# INSTRUCCIONES DE EJECUCIÓN:
#
# En Linux / macOS:
#   1. Abrir terminal en la raíz del proyecto.
#   2. chmod +x generate_feature.sh
#   3. ./generate_feature.sh
#
# En Windows:
#   1. Abrir "Git Bash" en la carpeta del proyecto.
#   2. ./generate_feature.sh
#
# ========================================================

# --- CONFIGURACIÓN ---
PROJECT_PACKAGE="com.minmyn.app.algo"
BASE_PATH=$(pwd)

# --- DETECCIÓN DE DIRECTORIO ---
if [ -d "app/src/main/java" ]; then
    SRC_DIR="app/src/main/java"
elif [ -d "app/src/main/kotlin" ]; then
    SRC_DIR="app/src/main/kotlin"
else
    echo "❌ Error: Carpeta src/main no encontrada."
    exit 1
fi

# 1. Entrada de datos
clear
echo "========================================"
echo "   ANDROID FEATURE GENERATOR (BASH)    "
echo "========================================"
read -p "Nombre del feature (ej. perfilUsuario): " FEATURE
if [ -z "$FEATURE" ]; then exit 1; fi

# Formateo de nombres
FEATURE_LOWER=$(echo "$FEATURE" | tr '[:upper:]' '[:lower:]')
FEATURE_CAPITALIZED="$(tr '[:lower:]' '[:upper:]' <<< ${FEATURE:0:1})${FEATURE:1}"

# Rutas
PACKAGE_PATH=${PROJECT_PACKAGE//./\/}
FINAL_PATH="$BASE_PATH/$SRC_DIR/$PACKAGE_PATH/$FEATURE_LOWER"
SCREEN_PATH="$FINAL_PATH/presentation/screen"
VM_PATH="$FINAL_PATH/presentation/viewmodels"

# Verificar existencia
if [ -d "$FINAL_PATH" ]; then
    echo "⚠️  El feature '$FEATURE_LOWER' ya existe. ¿Deseas continuar? (s/n)"
    read -r response
    if [[ ! "$response" =~ ^([sS][iI]|[sS])$ ]]; then exit 1; fi
fi

# 2. Crear carpetas
mkdir -p "$FINAL_PATH/data" "$FINAL_PATH/domain" "$SCREEN_PATH" "$VM_PATH"

# --- 3. CREAR VIEWMODEL.KT ---
VM_FILE="${FEATURE_CAPITALIZED}ViewModel.kt"
cat <<EOF > "$VM_PATH/$VM_FILE"
package $PROJECT_PACKAGE.$FEATURE_LOWER.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ${FEATURE_CAPITALIZED}ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(0)
    val uiState: StateFlow<Int> = _uiState
}
EOF

# --- 4. CREAR SCREEN.KT (CON PREVIEW Y THEME) ---
SCREEN_FILE="${FEATURE_CAPITALIZED}Screen.kt"
cat <<EOF > "$SCREEN_PATH/$SCREEN_FILE"
package $PROJECT_PACKAGE.$FEATURE_LOWER.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import $PROJECT_PACKAGE.$FEATURE_LOWER.presentation.viewmodels.${FEATURE_CAPITALIZED}ViewModel
// import $PROJECT_PACKAGE.ui.theme.AppTheme // Ajusta según tu ruta real de Theme

@Composable
fun ${FEATURE_CAPITALIZED}Screen(
    viewModel: ${FEATURE_CAPITALIZED}ViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ${FEATURE_CAPITALIZED}Content(state = state)
}

@Composable
fun ${FEATURE_CAPITALIZED}Content(
    state: Int
) {
    // AppTheme { // Comentado para evitar errores si la ruta cambia
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Feature: ${FEATURE_CAPITALIZED} (State: \$state)")
            }
        }
    // }
}

@Preview(showBackground = true)
@Composable
fun ${FEATURE_CAPITALIZED}Preview() {
    ${FEATURE_CAPITALIZED}Content(state = 0)
}
EOF

echo "----------------------------------------"
echo "✅ Feature '$FEATURE_LOWER' generado con éxito"
echo "----------------------------------------"
echo "📂 Estructura creada:"
echo "   $FEATURE_LOWER/"
echo "   ├── data/"
echo "   ├── domain/"
echo "   └── presentation/"
echo "       ├── screen/${FEATURE_CAPITALIZED}Screen.kt (UI + Preview)"
echo "       └── viewmodels/${FEATURE_CAPITALIZED}ViewModel.kt"
echo "----------------------------------------"