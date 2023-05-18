package com.example.mystore.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mystore.ui.login.LoginScreen
import com.example.mystore.ui.products.ProductDetailsScreen
import com.example.mystore.ui.theme.MyStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreTheme {
                val navController = rememberNavController()
                var canGoBack by remember { mutableStateOf(false) }
                navController.addOnDestinationChangedListener { _, _, _ ->
                    canGoBack =
                        navController.previousBackStackEntry?.destination?.route != "Login"
                                && navController.previousBackStackEntry != null
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "Login"
                        ) {
                            composable(route = "Login") {
                                LoginScreen(navigateToProductDetailsScreen = {
                                    navController.navigate("ProductDetailsScreen")
                                })
                            }
                            composable(route = "ProductDetailsScreen") {
                                ProductDetailsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}