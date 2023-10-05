package com.paradoxo.materialgram.domain

import com.paradoxo.materialgram.data.model.VideoResponse
import com.paradoxo.materialgram.domain.model.Reels

fun VideoResponse.toReels(): Reels = Reels(
    video = video,
    thumbnail = thumbnail,
    basePost = basePost
)