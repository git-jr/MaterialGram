package com.paradoxo.materialgram.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.materialgram.domain.PostUseCase
import com.paradoxo.materialgram.domain.model.Media
import com.paradoxo.materialgram.presentation.screens.voicedetection.AudioClassifierEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

        val adIndex = with(_uiState.value) {
            if (currentVisibleItem == posts.size - 1) currentVisibleItem else currentVisibleItem + 1
        }

        _uiState.update { currentState ->
            val newPosts = currentState.posts.toMutableList().apply {
                val currentPost = this[adIndex]
                this[adIndex] = currentPost.copy(
                    images = listOf(Media(url = url.first, description = "")),
                    basePost = currentPost.basePost.copy(description = url.second)
                )
            }

            currentState.copy(posts = newPosts)
        }
    }


    fun getUrlByType(type: AudioClassifierEnum): Pair<String, String> {
        val coffe_image = "https://i.imgur.com/R1btLfH.jpeg"
        val palmeiras_image = "https://i.imgur.com/zPDltKp.jpeg"
        val netflix_image = "https://i.imgur.com/Srlv3a4.jpeg"


        return when (type) {
            AudioClassifierEnum.PALMEIRAS -> Pair(palmeiras_image, "Acesse o site e compre agora")
            AudioClassifierEnum.NETFLIX -> Pair(netflix_image, "Primeiro mês grátis")
            AudioClassifierEnum.COFFEE -> Pair(coffe_image, " Peça do conforto do seu lar!")
        }
    }
}