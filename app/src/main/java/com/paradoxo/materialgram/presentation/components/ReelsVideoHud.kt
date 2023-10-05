package com.paradoxo.materialgram.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.paradoxo.materialgram.R
import com.paradoxo.materialgram.domain.model.BasePost


@Composable
fun VideoHud(reels: BasePost) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .width(70.dp)
                .padding(vertical = 16.dp)
        ) {
            ItemIcon(Icons.Default.ThumbUp, reels.likes.toString())

            ItemIcon(Icons.Default.ThumbDown, "Não gostei")

            ItemIcon(Icons.Default.Comment, reels.comments.toString())

            ItemIcon(Icons.Default.Send, "Compartilhar")

            AsyncImageWithShimmer(
                data = reels.user.avatar,
                contentDescription = "foto de perfil da conta",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(20))
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp, bottom = 16.dp,
                    start = 10.dp, end = 80.dp
                )
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    AsyncImageWithShimmer(
                        data = reels.user.avatar,
                        contentDescription = "Foto de perfil da conta",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = reels.user.name,
                        color = colorResource(id = R.color.reels_icon),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    )

                    Text(
                        text = "Inscrever-se",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = colorResource(id = R.color.reels_text),
                        modifier = Modifier
                            .background(
                                shape = CircleShape,
                                color = colorResource(id = R.color.reels_icon)
                            )
                            .padding(8.dp)
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = reels.description,
                    color = colorResource(id = R.color.reels_icon),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }
        }
    }
}


@Composable
private fun ItemIcon(icon: ImageVector, text: String) {
    Column(
        modifier = Modifier.size(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon, contentDescription = text,
            tint = colorResource(id = R.color.reels_icon),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.reels_icon),
            fontSize = MaterialTheme.typography.labelMedium.fontSize
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}