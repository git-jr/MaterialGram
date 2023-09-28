package com.paradoxo.materialgram.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.paradoxo.materialgram.presentation.components.HomeAppBar
import com.paradoxo.materialgram.presentation.components.HomeBottomBar
import com.paradoxo.materialgram.presentation.components.HomeContent
import com.paradoxo.materialgram.presentation.components.HomeFAB
import com.paradoxo.materialgram.presentation.theme.MaterialGramTheme

@Composable
fun HomeScreen(state: HomeUiState) {
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        floatingActionButton = {
            HomeFAB()
        },
        bottomBar = {
            HomeBottomBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HomeContent(state)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialGramTheme {
        HomeScreen(HomeUiState(emptyList()))
    }
}