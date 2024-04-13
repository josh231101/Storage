package com.example.misdatos

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.misdatos.ui.theme.MisDatosTheme
import com.example.misdatos.viewmodels.PreferencesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context: Context = LocalContext.current
            val preferencesViewModel = PreferencesViewModel(context)

            MisDatosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingsView(preferencesViewModel)
                }
            }
        }
    }
}

@Composable
fun SettingsView(preferencesViewModel: PreferencesViewModel) {

    var nombre by rememberSaveable {
        mutableStateOf("")
    }

    var edad by rememberSaveable {
        mutableStateOf("")
    }

    var altura by rememberSaveable {
        mutableStateOf("")
    }

    var peso by rememberSaveable {
        mutableStateOf("")
    }

    var hobby by rememberSaveable {
        mutableStateOf("")
    }

    var corrutineScope = rememberCoroutineScope()

    var savedAge = preferencesViewModel.age.collectAsState(initial = 0)
    var savedName = preferencesViewModel.name.collectAsState(initial = "")
    var savedHeight = preferencesViewModel.height.collectAsState(initial = 0.0)
    var savedWeight = preferencesViewModel.weight.collectAsState(initial = 0.0)
    var savedHobby = preferencesViewModel.hobby.collectAsState(initial = "")

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (savedName.value == "") {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
        }

        if (savedAge.value == 0) {
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
        }

        if (savedHeight.value == 0.0) {
            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
        }

        if (savedWeight.value == 0.0) {
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
        }

        if (savedHobby.value == "") {
            OutlinedTextField(
                value = hobby,
                onValueChange = {hobby = it},
                label = { Text("Hobby") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
        }

        if (savedName.value == "" && savedAge.value == 0 && savedHeight.value == 0.0 && savedWeight.value == 0.0 && savedHobby.value == "") {
            Button(
                onClick = {
                    corrutineScope.launch {
                        if (edad.toIntOrNull() != null && altura.toFloatOrNull() != null && peso.toFloatOrNull() != null) {
                            preferencesViewModel.setNameAndAge(
                                nombre,
                                edad.toInt(),
                                altura.toFloat(),
                                peso.toFloat(),
                                hobby
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "Guardar datos")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Nombre: ${savedName.value}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Edad: ${savedAge.value}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Altura: ${savedHeight.value}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Peso: ${savedWeight.value}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Hobby: ${savedHobby.value}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val context: Context = LocalContext.current
    val preferencesViewModel = PreferencesViewModel(context)

    MisDatosTheme {
        SettingsView(preferencesViewModel)
    }
}