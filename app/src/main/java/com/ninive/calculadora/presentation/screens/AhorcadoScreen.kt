package com.ninive.calculadora.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ninive.calculadora.presentation.viewmodels.AhorcadoViewModel


@Composable
fun AhorcadoScreen(viewModel: AhorcadoViewModel = viewModel()) {

    val letra by viewModel.letra.collectAsStateWithLifecycle()
    val errores by viewModel.errores.collectAsStateWithLifecycle()
    val mensaje by viewModel.mensaje.collectAsStateWithLifecycle()
    val letrasAdivinadas by viewModel.letrasAdivinadas.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Juego del Ahorcado", fontSize = 26.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = viewModel.palabraVisible(),
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Errores: $errores / 6")

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = letra,
            onValueChange = { viewModel.onChangeLetra(it) },
            label = { Text("Ingresa una letra") },
            enabled = mensaje.isEmpty()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { viewModel.probarLetra() },
            enabled = mensaje.isEmpty(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Probar letra")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                fontSize = 20.sp,
                color = if (mensaje.contains("Ganaste")) Color.Green else Color.Red
            )
        }
    }
}

private fun AhorcadoViewModel.probarLetra() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun AhorcadoScreenPreview() {
    AhorcadoScreen()
}