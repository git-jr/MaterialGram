@file:OptIn(ExperimentalFoundationApi::class)

package com.paradoxo.materialgram.presentation.screens.reels

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.paradoxo.materialgram.R
import com.paradoxo.materialgram.presentation.components.VideoHud

@OptIn(ExperimentalMaterial3Api::class)
@UnstableApi
@Composable
fun ReelsScreen() {
    val viewModel = viewModel<ReelsViewModel>()
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        VideoPlayer(viewModel)

        LoadOverlay(state)

        Column(Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(pageCount = {
                state.reels.size
            })

            val currentReels = state.reels[pagerState.currentPage]

            LaunchedEffect(pagerState.getOffsetFractionForPage(0)) {
                val pageIsTotalVisible = pagerState.currentPageOffsetFraction == 0.0f
                if (pageIsTotalVisible) {
                    viewModel.playVideo(currentReels.video)
                    viewModel.showLoadAnimation()
                }
            }


            Box(Modifier.fillMaxSize()) {
                TopAppBar(
                    title = {
                        Text(
                            text = "Reels",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            color = colorResource(id = R.color.reels_icon)
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Buscar",
                                tint = colorResource(id = R.color.reels_icon),
                            )
                        }


                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Camêra",
                                tint = colorResource(id = R.color.reels_icon),
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = "Mais opções",
                                tint = colorResource(id = R.color.reels_icon),
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                )

                VerticalPager(
                    state = pagerState,
                ) {
                    VideoHud(currentReels.basePost)
                }
            }

        }
    }
}


@UnstableApi
@Composable
private fun VideoPlayer(viewModel: ReelsViewModel) {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = viewModel.player
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                        it.player?.play()
                    }

                    else -> Unit
                }
            }
        )

    }
}

@Composable
private fun LoadOverlay(state: ReelsUiState) {
    AnimatedVisibility(
        visible = state.showLoadAnimation,
        enter = fadeIn(tween(state.loadAnimationTime / 3)),
        exit = fadeOut(tween(state.loadAnimationTime)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
    }
}