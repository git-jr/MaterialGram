package com.paradoxo.materialgram.presentation.screens.reels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.materialgram.domain.VideoUseCase
import com.paradoxo.materialgram.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReelsViewModel @Inject constructor(
    private val videoUseCase: VideoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReelsUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadVideos()
        }
    }


    private suspend fun loadVideos() {
        videoUseCase.getVideos().collect { videos ->
            _uiState.value = _uiState.value.copy(
                videos = videos
            )
        }
    }
}


data class ReelsUiState(
    val videos: List<Video>
)