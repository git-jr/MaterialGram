package com.paradoxo.materialgram.presentation.screens.feed

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paradoxo.materialgram.R
import com.paradoxo.materialgram.domain.model.BasePost
import com.paradoxo.materialgram.domain.model.Media
import com.paradoxo.materialgram.domain.model.Post
import com.paradoxo.materialgram.presentation.components.AsyncImageWithShimmer
import com.paradoxo.materialgram.presentation.components.ItemCarouselView
import com.paradoxo.materialgram.presentation.components.ItemSingleImage
import com.paradoxo.materialgram.presentation.components.StoryContainer


@Composable
fun ListPosts(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        item {
            StoryContainer()
        }
        items(posts) { post ->
            ItemPost(post)
        }
    }
}

@Composable
private fun ItemPost(post: Post) {
    Spacer(modifier = Modifier.height(8.dp))

    Column {
        PostHeader(post)
        Spacer(modifier = Modifier.height(8.dp))
        PostMedias(post.images)
        PostMetadata(post.basePost)
    }
}

@Composable
private fun PostHeader(post: Post) {
    ListItem(
        headlineContent = {
            Text(
                text = post.basePost.user.name,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                fontWeight = FontWeight.Medium,
            )
        },
        leadingContent = {
            AsyncImageWithShimmer(
                post.basePost.user.avatar, "foto de perfil ${post.basePost.user.name}",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(30.dp)),
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "Mais opções",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    )
}

@Composable
private fun PostMedias(medias: List<Media>) {
    Column(Modifier.padding(horizontal = 8.dp)) {
        if (medias.size > 1) {
            ItemCarouselView(medias)
        } else {
            ItemSingleImage(
                medias.first().url,
                medias.first().description
            )
        }
    }
}

@Composable
private fun PostMetadata(post: BasePost) {
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
        text = pluralStringResource(
            id = R.plurals.likes_count,
            count = post.likes,
            post.likes
        ),
        modifier = Modifier.padding(start = 16.dp),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
    Row {

        val nameWithDescription = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(post.user.name)
            }
            append(" ")
            append(post.description)
        }

        Text(
            text = nameWithDescription,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
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
