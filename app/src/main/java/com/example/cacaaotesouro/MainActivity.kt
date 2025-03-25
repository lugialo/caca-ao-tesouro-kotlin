package com.example.cacaaotesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cacaaotesouro.ui.theme.CacaAoTesouroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController, startDestination = "idTela01"
            ) {
                composable("idTela01"){
                    Tela(
                        "Home",
                        clickB1 = {navController.navigate("idTela02")},
                        clickB2 = {navController.navigate("idTela03")}
                    )
                }
                composable("idTela02"){
                    Tela(
                        "Tela 02",
                        clickB1 = {navController.navigate("idTela01")},
                        clickB2 = {navController.navigate("idTela03")}
                    )
                }
                composable("idTela03"){
                    Tela(
                        "Tela 03",
                        clickB1 = {navController.navigate("idTela01")},
                        clickB2 = {navController.navigate("idTela02")}
                    )
                }
            }
        }
    }
}


@Composable
fun Tela(name: String,
         clickB1: () -> Unit,
         clickB2: () -> Unit
)
{
    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(text = name)
        Button(onClick = {
            clickB1()
        }) {
            Text(text = "B1")
        }
        Button(onClick = {
            clickB2()
        }) {
            Text(text = "B2")
        }
    }
}