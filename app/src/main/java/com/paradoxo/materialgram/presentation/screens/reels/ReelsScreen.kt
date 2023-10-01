package com.paradoxo.materialgram.presentation.screens.reels

import android.media.MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.paradoxo.materialgram.R
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

        val inTest = false
        if (inTest) {
            AsyncImage(
                model = video.thumbnail,
                contentDescription = video.description,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            val context = LocalContext.current
            val exoPlayer = remember {
                val mediaItem = MediaItem.Builder()
                    .setUri(video.url)
                    .build()
                ExoPlayer.Builder(context).build().apply {
                    repeatMode = REPEAT_MODE_ALL
                    videoScalingMode = VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                    playWhenReady = true
                    setMediaItem(mediaItem)
                    prepare()

                }
            }

// colocar VideoUI aqui com box do vídeo

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

                VideoUI()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoUI() {
    Column(Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                // Texto Reels - Icone camera (Topo)
            },
            bottomBar = {
                // foto do usuário - nome do usuário - botão de seguir (parte de baixo)
                // descrição do vídeo

            }
        ) { paddingValues ->
            // No canto esquerdo inferior de cima pra baixo: Like, Deslike, Comentários, Compartilhar, Remix, Foto de perfil

            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    ItemIcon(Icons.Default.ThumbUp, "111 mil")

                    ItemIcon(Icons.Default.ThumbDown, "Não gostei")

                    ItemIcon(Icons.Default.Comment, "777")

                    ItemIcon(Icons.Default.Send, "Compartilhar")

                    AsyncImage(
                        model = R.drawable.ic_launcher_background,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(20))
                    )

                }

            }
        }

    }
}

@Composable
private fun ItemIcon(icon: ImageVector, text: String) {
    Column(
        modifier = Modifier.size(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon, "Like",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview
@Composable
fun VideoUIPreview() {
    VideoUI()
}
