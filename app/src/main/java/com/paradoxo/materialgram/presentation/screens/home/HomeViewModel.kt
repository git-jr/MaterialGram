package com.paradoxo.materialgram.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.materialgram.domain.PostUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var postUseCase: PostUseCaseImpl? = null

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
            postUseCase?.getPosts()?.collect { posts ->
                _uiState.value = _uiState.value.copy(posts = posts)
            }
        }
    }

    fun setRepository(postRepositoryImpl: PostUseCaseImpl) {
        postUseCase = postRepositoryImpl
        loadPosts()
    }
}