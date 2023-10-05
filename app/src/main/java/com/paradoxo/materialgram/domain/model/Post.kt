package com.paradoxo.materialgram.domain.model

data class Post(
    val basePost: BasePost,
    val images: List<Media>,
)

