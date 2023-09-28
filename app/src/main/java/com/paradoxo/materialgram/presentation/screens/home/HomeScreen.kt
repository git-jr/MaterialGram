package com.paradoxo.materialgram.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paradoxo.materialgram.presentation.components.HomeBottomBar
import com.paradoxo.materialgram.presentation.components.HomeFAB
import com.paradoxo.materialgram.presentation.components.HomeSearchAppBar
import com.paradoxo.materialgram.presentation.components.HomeTabsAppBar
import com.paradoxo.materialgram.presentation.screens.feed.FeedScreen
import com.paradoxo.materialgram.presentation.screens.reels.ReelsScreen
import com.paradoxo.materialgram.presentation.theme.MaterialGramTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val homeViewModel = viewModel<HomeViewModel>()
    val state by homeViewModel.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollBehaviorTabs = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehaviorTabs.nestedScrollConnection)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                HomeSearchAppBar(scrollBehavior = scrollBehavior)
                HomeTabsAppBar(
                    scrollBehavior = scrollBehaviorTabs,
                    onSelectedTab = { index ->
                        homeViewModel.changeSelectedTab(index)
                    }
                )
            }
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
            if (state.showFeed) {
                FeedScreen(state)
            } else {
                ReelsScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialGramTheme {
        HomeScreen()
    }
}