package com.paradoxo.materialgram.presentation.screens.reels

import com.paradoxo.materialgram.domain.model.Reels

data class ReelsUiState(
    val reels: List<Reels>,
    val showLoadAnimation: Boolean = false,
    val loadAnimationTime: Int = 800
)