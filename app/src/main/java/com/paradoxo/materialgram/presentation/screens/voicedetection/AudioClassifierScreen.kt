package com.paradoxo.materialgram.presentation.screens.voicedetection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//@Composable
//fun AudioClassifierScreen(modifier: Modifier = Modifier) {
//    val viewModel = hiltViewModel<AudioClassifierViewModel>()
//    val state by viewModel.uiState.collectAsState()
//
//    Column(
//        modifier = modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Audio Classifier",
//            style = MaterialTheme.typography.headlineMedium,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//
//        state.error?.let {
//            Text(
//                text = "Erro: $it",
//                style = MaterialTheme.typography.headlineMedium,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//        }
//
//        state.results.forEach {
//            Text(
//                text = "Class: ${it.categoryName()}, Score: ${it.score()}",
//                style = MaterialTheme.typography.headlineMedium,
//                color = MaterialTheme.colorScheme.onSurface,
//                modifier = modifier.padding(8.dp)
//            )
//        }
//    }
//}