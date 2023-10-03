package com.paradoxo.materialgram.presentation.screens.reels

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
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paradoxo.materialgram.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun VideoHud() {
    Column(Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
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
            },
        ) { paddingValues ->
            Box(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .width(70.dp)
                        .padding(vertical = 16.dp)
                ) {
                    ItemIcon(Icons.Default.ThumbUp, "111 mil")

                    ItemIcon(Icons.Default.ThumbDown, "Não gostei")

                    ItemIcon(Icons.Default.Comment, "777")

                    ItemIcon(Icons.Default.Send, "Compartilhar")

                    AsyncImage(
                        model = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png",
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
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
                            AsyncImage(
                                model = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png",
                                contentDescription = "Foto de perfil da conta",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = "@CiênciaTodoDia",
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
                                        color = MaterialTheme.colorScheme.primaryContainer
                                    )
                                    .padding(8.dp)
                            )

                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(id = R.string.sample_lorem_ipsum),
                            color = colorResource(id = R.color.reels_icon),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                    }
                }
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

@Preview
@Composable
fun VideoHudPreview() {
    VideoHud()
}
