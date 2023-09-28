package com.paradoxo.materialgram.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paradoxo.materialgram.data.PostLocalDataSource
import com.paradoxo.materialgram.data.PostRepositoryImpl
import com.paradoxo.materialgram.domain.PostUseCaseImpl
import com.paradoxo.materialgram.presentation.screens.home.HomeScreen
import com.paradoxo.materialgram.presentation.screens.home.HomeViewModel
import com.paradoxo.materialgram.presentation.theme.MaterialGramTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialGramTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    manual dependency injection for now
                    val homeViewModel: HomeViewModel = viewModel()
                    homeViewModel.setRepository(
                        PostUseCaseImpl(
                            PostRepositoryImpl(
                                PostLocalDataSource()
                            )
                        )
                    )
                    val state by homeViewModel.uiState.collectAsState()

                    HomeScreen(state)
                }
            }
        }
    }
}




