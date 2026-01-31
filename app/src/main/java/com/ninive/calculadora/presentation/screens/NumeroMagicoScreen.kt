package com.ninive.calculadora.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ninive.calculadora.presentation.viewmodels.NumeroMagicoViewModel

@Composable
fun NumeroMagicoScreen(miViewModel: NumeroMagicoViewModel = viewModel()) {
    val datos by miViewModel.estado.collectAsState()
    var entradaUsuario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Juego: Número Mágico", fontSize = 26.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Intentos que te quedan: ${datos.intentosRestantes}",
            color = if (datos.intentosRestantes <= 2) Color.Red else Color.Unspecified
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = datos.mensajePista,
            fontSize = 20.sp,
            color = if (datos.adivinado) Color(0xFF2E7D32) else Color.DarkGray
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = entradaUsuario,
            onValueChange = { if (it.all { char -> char.isDigit() }) entradaUsuario = it },
            label = { Text("Escribe aquí tu número") },
            enabled = !datos.terminado,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                miViewModel.jugarNumeroMagico(entradaUsuario)
                entradaUsuario = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = entradaUsuario.isNotEmpty() && !datos.terminado
        ) {
            Text("Verificar Intento")
        }

        if (datos.terminado) {
            Spacer(modifier = Modifier.height(20.dp))

            if (!datos.adivinado) {
                Text("Se agotaron los intentos", color = Color.Red)
            }

            Button(
                onClick = { miViewModel.reiniciarNumeroMagico() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Nuevo Juego")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumeroMagicoScreenPreviw(){
    NumeroMagicoScreen()
}