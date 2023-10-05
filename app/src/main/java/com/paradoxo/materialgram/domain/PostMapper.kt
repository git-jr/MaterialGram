package com.paradoxo.materialgram.domain

import com.paradoxo.materialgram.data.model.PostResponse
import com.paradoxo.materialgram.domain.model.Post

fun PostResponse.toPost(): Post = Post(
    basePost = basePost,
    images = medias
)