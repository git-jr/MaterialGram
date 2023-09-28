package com.paradoxo.materialgram.presentation.screens.home

import com.paradoxo.materialgram.domain.model.Post

data class HomeUiState(
    val posts: List<Post>,
    val showFeed: Boolean = true
)