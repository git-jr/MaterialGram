package com.paradoxo.materialgram.presentation.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paradoxo.materialgram.presentation.screens.feed.FeedScreen
import com.paradoxo.materialgram.presentation.screens.reels.ReelsScreen
import com.paradoxo.materialgram.presentation.theme.MaterialGramTheme


@Composable
fun HomeScreen(onBack: () -> Unit) {
    val homeViewModel = viewModel<HomeViewModel>()
    val state by homeViewModel.uiState.collectAsState()

    // Handle back press
    BackHandler {
        if (!state.showFeed) {
            homeViewModel.changeSelectedTab(0)
        } else {
            onBack()
        }
    }

    Crossfade(targetState = state.showFeed, label = "") { showFeed ->
        if (showFeed) {
            FeedScreen(state.posts) { tab ->
                homeViewModel.changeSelectedTab(tab)
            }
        } else {
            ReelsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialGramTheme {
        HomeScreen {}
    }
}