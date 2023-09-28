package com.paradoxo.materialgram.domain.model

data class Post(
    val medias: List<Image>, val description: String,
    val likes: Int, val comments: Int, val time: String
)

