package com.example.cacaaotesouro

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.Uri
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.gif.AnimatedImageDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "TelaInicial"
            ) {
                composable("TelaInicial") {
                    TelaInicial(
                        clickButtonStart = { navController.navigate("TelaDica01") }
                    )
                }
                composable("TelaDica01") {
                    TelaDica(
                        name = "Dica 01",
                        pergunta = "O mais famoso vem da vaca",
                        clickProxPista = { navController.navigate("TelaDica02") },
                        clickVoltar = { navController.navigate("TelaInicial") },
                        navController = navController
                    )
                }
                composable("TelaDica02") {
                    TelaDica(
                        name = "Dica 02",
                        pergunta = "Também pode se originar de outros animais",
                        clickProxPista = { navController.navigate("TelaDica03") },
                        clickVoltar = { navController.navigate("TelaDica01") },
                        navController = navController
                    )
                }
                composable("TelaDica03") {
                    TelaDica(
                        name = "Dica 03",
                        pergunta = "É muito apreciado junto com vinho",
                        clickVoltar = { navController.navigate("TelaDica02") },
                        navController = navController
                    )
                }
                composable("TelaFinal") {
                    TelaFinal()
                }
                composable("TelaErro") {
                    TelaErro()
                }
            }
        }
    }
}

@Composable
fun TelaDica(
    name: String,
    pergunta: String,
    clickProxPista: (() -> Unit)? = null,
    clickVoltar: () -> Unit,
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = name)
        Text(text = pergunta)

        var resposta by remember { mutableStateOf("") }
        TextField(
            value = resposta,
            onValueChange = { resposta = it },
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                if (resposta.lowercase() == "queijo") {
                    navController.navigate("TelaFinal")
                } else {
                    navController.navigate("TelaErro")
                }
            }) {
                Text("Inserir resposta")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                enabled = clickProxPista != null,
                onClick = { clickProxPista?.invoke() }
            ) {
                Text("Próxima pista")
            }

            Button(onClick = clickVoltar) {
                Text("Voltar")
            }
        }
    }
}

@Composable
fun TelaInicial(clickButtonStart: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar caça ao tesouro")
        Button(onClick = clickButtonStart) {
            Text(text = "Começar")
        }
    }
}

@Composable
fun TelaErro() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Resposta incorreta. Tente novamente.")
    }
}

@Composable
fun TelaFinal() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GifImage(drawableResId = R.drawable.gatojoia)
        Text(text = "Parabéns! Você ganhou.")
    }
}

@Composable
fun GifImage(drawableResId: Int) {
    coil3.compose.AsyncImage(
        model = drawableResId,
        contentDescription = "gif"
    )
}