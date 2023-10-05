package com.paradoxo.materialgram.data.model

import com.paradoxo.materialgram.domain.model.BasePost
import com.paradoxo.materialgram.domain.model.Media

data class PostResponse(
    val basePost: BasePost,
    val medias: List<Media>,
)

