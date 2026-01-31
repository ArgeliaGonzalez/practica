package com.ninive.calculadora.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AhorcadoViewModel : ViewModel() {
    private val palabraSecreta = "hola"
    private val maxErrores = 6

    private val _letra = MutableStateFlow("")
    val letra = _letra.asStateFlow()

    private val _letrasAdivinadas = MutableStateFlow(setOf<Char>())
    val letrasAdivinadas = _letrasAdivinadas.asStateFlow()

    private val _errores = MutableStateFlow(0)
    val errores = _errores.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje = _mensaje.asStateFlow()

    fun onChangeLetra(it: String) {
        if (it.length <= 1) {
            _letra.value = it.lowercase()
        }
    }

    fun onProbarLetra() {
        if (_letra.value.isEmpty()) return

        val char = _letra.value[0]

        if (!_letrasAdivinadas.value.contains(char)) {
            _letrasAdivinadas.value = _letrasAdivinadas.value + char

            if (!palabraSecreta.contains(char)) {
                _errores.value = _errores.value + 1
            }
        }

        verificarEstado()
        _letra.value = ""
    }

    private fun verificarEstado() {
        if (palabraSecreta.all { _letrasAdivinadas.value.contains(it) }) {
            _mensaje.value = "Ganaste"
        } else if (_errores.value >= maxErrores) {
            _mensaje.value = "Perdiste. La palabra era: $palabraSecreta"
        }
    }

    fun palabraVisible(): String {
        return palabraSecreta.map {
            if (_letrasAdivinadas.value.contains(it)) it else '_'
        }.joinToString(" ")
    }
}