package com.paradoxo.materialgram.presentation.screens.reels

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.paradoxo.materialgram.domain.model.Video
import com.paradoxo.materialgram.domain.model.videos


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen() {
    val pagerState = rememberPagerState(pageCount = {
        videos.size
    })

    VerticalPager(state = pagerState) {
        Column {
            ItemReels(
                video = videos[pagerState.currentPage]
            )
        }
    }
}


@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private fun ItemReels(video: Video) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val context = LocalContext.current
        val exoPlayer = remember {
            val mediaItem = MediaItem.Builder()
                .setUri(video.url)
                .build()
            ExoPlayer.Builder(context).build().apply {
                repeatMode = REPEAT_MODE_ALL
                videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                playWhenReady = true
                setMediaItem(mediaItem)
                prepare()

            }
        }

        Box {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
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
                        }

                        else -> Unit
                    }
                }
            )
            VideoHud()
        }
    }
}
