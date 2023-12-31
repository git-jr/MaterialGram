package com.paradoxo.materialgram.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ItemSingleImage(imageUrl: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImageWithShimmer(
            imageUrl, description,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(30.dp)),
        )
    }
}

