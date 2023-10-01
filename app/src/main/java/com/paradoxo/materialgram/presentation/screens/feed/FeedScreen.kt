package com.paradoxo.materialgram.presentation.screens.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.Reply
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paradoxo.materialgram.domain.model.Post
import com.paradoxo.materialgram.presentation.components.HomeBottomBar
import com.paradoxo.materialgram.presentation.components.HomeFAB
import com.paradoxo.materialgram.presentation.components.HomeSearchAppBar
import com.paradoxo.materialgram.presentation.components.HomeTabsAppBar
import com.paradoxo.materialgram.presentation.components.ItemCarouselView
import com.paradoxo.materialgram.presentation.components.ItemSingleImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    posts: List<Post>,
    onSelectedTab: (Int) -> Unit = {}
) {
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
                        onSelectedTab(index)
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

            ListPosts(posts)
        }
    }


}

@Composable
private fun ListPosts(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(posts) { post ->
            ItemPost(post)
        }
    }
}

@Composable
private fun ItemPost(post: Post) {
    Column {
        Column(Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            if (post.medias.size > 1) {
                ItemCarouselView(post.medias)
            } else {
                ItemSingleImage(
                    post.medias.first().imageUrl,
                    post.medias.first().imageDescription
                )
            }
        }
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Like",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Outlined.ModeComment,
                    contentDescription = "Comment",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    imageVector = Icons.Outlined.Reply,
                    contentDescription = "Share",
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer {
                            rotationY = 180f
                        },
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Text(
            text = "1033 likes",
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(
                text = "Loki",
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "hj nem odin na causa",
                modifier = Modifier.padding(start = 4.dp),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
            )
        }

        Text(
            text = "Há 6 horas",
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
