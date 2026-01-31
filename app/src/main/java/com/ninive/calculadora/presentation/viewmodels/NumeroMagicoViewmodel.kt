package com.ninive.calculadora.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class NumeroMagicoViewModel : ViewModel() {
    private val numeroSecreto = (1..100).random(Random(System.nanoTime()))

    private val _estado = MutableStateFlow(EstadoNumeroMagico())
    val estado: StateFlow<EstadoNumeroMagico> = _estado.asStateFlow()

    fun jugarNumeroMagico(valorIngresado: String) {
        val intento = valorIngresado.toIntOrNull()
        val actual = _estado.value

        if (intento == null || actual.intentosRestantes <= 0 || actual.adivinado) return

        val restantes = actual.intentosRestantes - 1
        val diferencia = Math.abs(intento - numeroSecreto)

        val pista = when {
            intento == numeroSecreto -> "¡Adivinaste el número!"
            intento > numeroSecreto -> {
                if (diferencia > 20) "Muy caliente, te pasaste por mucho"
                else "Caliente, cerca pero todavía no"
            }
            else -> {
                if (diferencia > 20) "Muy frío, vuelve a intentarlo"
                else "Frío, vuelve a intentarlo"
            }
        }

        _estado.value = actual.copy(
            mensajePista = pista,
            intentosRestantes = restantes,
            adivinado = intento == numeroSecreto,
            terminado = (restantes == 0 || intento == numeroSecreto)
        )
    }
    fun reiniciarNumeroMagico() {
        _estado.value = EstadoNumeroMagico()
    }
}

data class EstadoNumeroMagico(
    val mensajePista: String = "Adivina el número del 1 al 100",
    val intentosRestantes: Int = 5,
    val adivinado: Boolean = false,
    val terminado: Boolean = false
)