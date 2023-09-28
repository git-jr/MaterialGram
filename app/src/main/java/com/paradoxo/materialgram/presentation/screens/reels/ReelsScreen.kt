package com.paradoxo.materialgram.presentation.screens.reels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen() {

    val videos = listOf(
        Video(
            "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
            "Video 1"
        ),
        Video(
            "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
            "Video 2"
        ),
        Video(
            "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
            "Video 3"
        ),

        )

    val pageCount = videos.size
    val pagerState = rememberPagerState(pageCount = {
        videos.size
    })

    LaunchedEffect(pagerState.currentPage) {
        //
    }

    VerticalPager(state = pagerState) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            ItemReels(
                video = videos[pagerState.currentPage]
            )
        }
    }


}


data class Video(val url: String, val description: String)

@Composable
private fun ItemReels(video: Video) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        AsyncImage(
            model = video.url,
            contentDescription = video.description,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
