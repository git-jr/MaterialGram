package com.paradoxo.materialgram.presentation.screens.reels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.paradoxo.materialgram.domain.VideoUseCase
import com.paradoxo.materialgram.domain.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReelsViewModel @Inject constructor(
    private val videoUseCase: VideoUseCase,
    val player: Player
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReelsUiState(emptyList()))
    val uiState = _uiState.asStateFlow()

    init {
        player.prepare()

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

    fun playVideo(path: String) {
        val mediaItem = MediaItem.Builder()
            .setUri(path)
            .build()
        player.setMediaItem(mediaItem)
    }


    private fun resetLoadAnimation() {
        _uiState.value = _uiState.value.copy(
            showLoadAnimation = !_uiState.value.showLoadAnimation
        )
    }

    fun showLoadAnimation() {
        _uiState.value = _uiState.value.copy(
            showLoadAnimation = true
        )

        viewModelScope.launch {
            with(_uiState.value) {
                if (showLoadAnimation) {
                    delay(loadAnimationTime.toLong())
                    resetLoadAnimation()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}