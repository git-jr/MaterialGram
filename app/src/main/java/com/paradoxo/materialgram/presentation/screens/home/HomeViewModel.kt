package com.paradoxo.materialgram.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradoxo.materialgram.domain.PostUseCase
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
}