package com.paradoxo.materialgram.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.paradoxo.materialgram.presentation.adapter.ImageAdapter
import com.paradoxo.materialgram.domain.model.Media

@Composable
fun ItemCarouselView(medias: List<Media>) {
    AndroidView(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        factory = { context ->
            RecyclerView(context).apply {
                val adapter = ImageAdapter(context, medias)

                this.layoutManager = CarouselLayoutManager()
                this.adapter = adapter
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun CustomViewPreview() {
    Column(Modifier.fillMaxSize()) {
        ItemCarouselView(emptyList())
    }
}