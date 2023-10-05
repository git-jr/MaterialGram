package com.paradoxo.materialgram.data.model

import com.paradoxo.materialgram.domain.model.BasePost


data class VideoResponse(
    val video: String,
    val thumbnail: String,
    val basePost: BasePost,
)

