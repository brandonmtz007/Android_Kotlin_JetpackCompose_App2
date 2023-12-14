package com.example.proyectojetpackcompostradiobutton5f2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectojetpackcompostradiobutton5f2023.ui.theme.ProyectoJetPackCompostradiobutton5F2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoJetPackCompostradiobutton5F2023Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator() // Use the Calculator composable here
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        val operations = listOf("Suma", "Resta", "Multiplicación")
        var selectedOperation by remember { mutableStateOf(operations[0]) }

        Column {
            operations.forEach { operation ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (operation == selectedOperation),
                            onClick = { selectedOperation = operation }
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (operation == selectedOperation),
                        onClick = { selectedOperation = operation }
                    )
                    Text(
                        text = operation,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                val n1: Float? = num1.toFloatOrNull()
                val n2: Float? = num2.toFloatOrNull()

                if (n1 != null && n2 != null) {
                    when (selectedOperation) {
                        "Suma" -> result = (n1 + n2).toString()
                        "Resta" -> result = (n1 - n2).toString()
                        "Multiplicación" -> result = (n1 * n2).toString()
                    }
                } else {
                    result = "Por favor, ingrese números válidos."
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Calcular")
        }

        if (result.isNotEmpty()) {
            Text(text = "Resultado: $result")
        }
    }
}
@Composable
@Preview(showBackground = true)
fun CalculatorPreview() {
    ProyectoJetPackCompostradiobutton5F2023Theme {
        Calculator()
    }
}