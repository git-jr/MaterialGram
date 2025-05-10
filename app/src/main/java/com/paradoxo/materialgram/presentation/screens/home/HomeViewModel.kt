package com.paradoxo.materialgram.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.materialgram.domain.PostUseCase
import com.paradoxo.materialgram.domain.model.Media
import com.paradoxo.materialgram.presentation.screens.voicedetection.AudioClassifierEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var postUseCase: PostUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            posts = emptyList()
        )
    )
    var uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            postUseCase.getPosts().collect { posts ->
                _uiState.value = _uiState.value.copy(posts = posts)
            }
        }
    }

    fun changeSelectedTab(indexTab: Int) {
        _uiState.value = _uiState.value.copy(showFeed = indexTab == 0)
    }

    fun setCurrentVisibleItem(index: Int) {
        _uiState.value = _uiState.value.copy(currentVisibleItem = index)
    }

    fun addAd(type: AudioClassifierEnum) {
        val url = getUrlByType(type)

        // pegar o index atual visvel e trocar a imagem
        val isEndList = _uiState.value.currentVisibleItem == _uiState.value.posts.size - 1

        val adIndex = if (isEndList) {
            _uiState.value.currentVisibleItem
        } else {
            _uiState.value.currentVisibleItem + 2
        }
        val currentPost = _uiState.value.posts[adIndex]
        val newPost = currentPost.copy(
            images = listOf(Media(url = url, description = ""))
        )
        val newPosts = _uiState.value.posts.toMutableList()
        newPosts[adIndex] = newPost
        _uiState.value = _uiState.value.copy(posts = newPosts)
        // Atualizar o post na base de dados

    }


    fun getUrlByType(type: AudioClassifierEnum): String {
        val AVVA_LOGO_URL = "https://play-lh.googleusercontent.com/uCS0NfmFV2jXLIuPWoafB1eE28xkiektuiLzKmSLFpI5tF7uL2PGHrt8u1OQb9kypA=w480-h960-rw"

        val PALMEIRAS_LOGO_URL = ""

        return when (type) {
            AudioClassifierEnum.PALMEIRAS -> PALMEIRAS_LOGO_URL
            AudioClassifierEnum.AVVA -> AVVA_LOGO_URL
        }
    }
}