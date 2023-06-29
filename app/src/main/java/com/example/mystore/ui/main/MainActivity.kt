package com.example.mystore.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mystore.ui.NavGraphs
import com.example.mystore.ui.theme.MyStoreTheme
import com.example.mystore.ui.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                    ) {
                        val navController = rememberNavController()
                        val navigateUp = navController::navigateUp
                        val viewModel: MainActivityViewModel = hiltViewModel()

                        val navHostEngine = rememberNavHostEngine()
                        DestinationsNavHost(
                            startRoute = if (viewModel.hasToken()) NavGraphs.home else LoginScreenDestination,
                            modifier = Modifier.padding(it),
                            engine = navHostEngine,
                            navController = navController,
                            dependenciesContainerBuilder = {
                                dependency(navigateUp)
                            },
                            navGraph = NavGraphs.root
                        )
                    }
                }
            }
        }
    }
}

@RootNavGraph
@NavGraph
annotation class HomeNavGraph(
    val start: Boolean = false
)

@RootNavGraph
@NavGraph
annotation class ShoppingCartNavGraph(
    val start: Boolean = false
)

@RootNavGraph
@NavGraph
annotation class ProfileNavGraph(
    val start: Boolean = false
)