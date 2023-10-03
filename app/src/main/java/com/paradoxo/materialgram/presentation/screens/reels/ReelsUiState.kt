package com.paradoxo.materialgram.presentation.screens.reels

import com.paradoxo.materialgram.domain.model.Video

data class ReelsUiState(
    val videos: List<Video>,
    val showLoadAnimation: Boolean = false,
    val loadAnimationTime: Int = 800
)