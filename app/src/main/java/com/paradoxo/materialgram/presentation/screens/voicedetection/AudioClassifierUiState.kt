package com.paradoxo.materialgram.presentation.screens.voicedetection

import com.google.mediapipe.tasks.components.containers.Category

data class AudioClassifierUiState(
    val results: List<Category> = emptyList(),
    val error: String? = null,
    val palmeirasDetected: Boolean = false,
    val avvADetected: Boolean = false,
)

enum class AudioClassifierEnum {
    PALMEIRAS,
    AVVA
}