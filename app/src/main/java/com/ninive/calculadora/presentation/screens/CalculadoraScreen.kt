package com.ninive.calculadora.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculadoraScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "500",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth()
        ) {

            FilaBotones("C", "=", "÷", "×")
            FilaBotones("7", "8", "9", "-")
            FilaBotones("4", "5", "6", "+")
            FilaBotones("1", "2", "3", "=")
            FilaBotones("0", ".", "", "")
        }
    }
}

@Composable
fun FilaBotones(vararg textos: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), 
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        textos.forEach { texto ->
            if (texto.isNotEmpty()) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = texto,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraScreenPreview() {
    CalculadoraScreen()
}


