package com.paradoxo.materialgram.presentation.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paradoxo.materialgram.presentation.components.HomeBottomBar
import com.paradoxo.materialgram.presentation.components.HomeFAB
import com.paradoxo.materialgram.presentation.components.HomeSearchAppBar
import com.paradoxo.materialgram.presentation.components.HomeTabsAppBar
import com.paradoxo.materialgram.presentation.screens.feed.ListPosts
import com.paradoxo.materialgram.presentation.screens.reels.ReelsScreen
import com.paradoxo.materialgram.presentation.theme.MaterialGramTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onBack: () -> Unit) {
    val homeViewModel = viewModel<HomeViewModel>()
    val state by homeViewModel.uiState.collectAsState()
    val showFeed = state.showFeed

    SetupOnBackPress(
        showFeed = showFeed,
        onBack = onBack,
        onChangeSelectedTab = {
            homeViewModel.changeSelectedTab(it)
        }
    )

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollBehaviorTabs = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehaviorTabs.nestedScrollConnection)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (showFeed) {
                HomeAppBar(scrollBehavior, scrollBehaviorTabs, onSelectedTab = { tab ->
                    homeViewModel.changeSelectedTab(tab)
                })
            }
        },
        floatingActionButton = {
            if (showFeed) {
                HomeFAB()
            }
        },
        bottomBar = {
            HomeBottomBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Crossfade(targetState = showFeed, label = "") { showFeed ->
                if (showFeed) {
                    ListPosts(state.posts)
                } else {
                    ReelsScreen()
                }
            }
        }
    }
}

@Composable
private fun SetupOnBackPress(
    showFeed: Boolean,
    onChangeSelectedTab: (Int) -> Unit,
    onBack: () -> Unit
) {
    BackHandler {
        if (!showFeed) {
            onChangeSelectedTab(0)
        } else {
            onBack()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    scrollBehaviorTabs: TopAppBarScrollBehavior,
    onSelectedTab: (Int) -> Unit
) {
    Column {
        HomeSearchAppBar(scrollBehavior = scrollBehavior)
        HomeTabsAppBar(
            scrollBehavior = scrollBehaviorTabs,
            onSelectedTab = { index ->
                onSelectedTab(index)
            }
        )
    }
}