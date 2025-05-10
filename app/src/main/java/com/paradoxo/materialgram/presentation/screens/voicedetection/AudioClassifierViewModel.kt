package com.paradoxo.materialgram.presentation.screens.voicedetection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mediapipe.tasks.components.containers.Category
import com.paradoxo.materialgram.presentation.screens.voicedetection.AudioClassifierHelper.ResultBundle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioClassifierViewModel @Inject constructor(
    private val audioClassifierHelper: AudioClassifierHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(AudioClassifierUiState())
    var uiState = _uiState.asStateFlow()

    init {
        audioClassifierHelper.initClassifier()
        setResultListener()
    }

    private fun setResultListener() {
        val resultListener = object : AudioClassifierHelper.ClassifierListener {
            override fun onResult(resultBundle: ResultBundle) {
                viewModelScope.launch {
                    resultBundle.results[0].classificationResults().first()
                        .classifications()?.get(0)?.categories()
                        ?.let { categoryList: List<Category> ->
                            _uiState.value = _uiState.value.copy(
                                results = categoryList,
                            )
                            Log.d(
                                "AudioClassifierViewModel",
                                "AudioClassifierViewModel Category List: $categoryList"
                            )

                            val palmeirasCategory =
                                categoryList.find { it.categoryName().contains("Palmeiras") }

                            if (palmeirasCategory != null && palmeirasCategory.score() > 0.75f) {
                                _uiState.value = _uiState.value.copy(
                                    palmeirasDetected = true,
                                )
                            } else {
                                _uiState.value = _uiState.value.copy(
                                    palmeirasDetected = false,
                                )
                            }

                            val avvACategory =
                                categoryList.find { it.categoryName().contains("AvvA") }

                            if (avvACategory != null && avvACategory.score() > 0.70f) {
                                _uiState.value = _uiState.value.copy(
                                    avvADetected = true,
                                )
                            } else {
                                _uiState.value = _uiState.value.copy(
                                    avvADetected = false,
                                )
                            }
                        }

                }
            }

            override fun onError(error: String) {
                _uiState.value = _uiState.value.copy(
                    error = error,
                )
                Log.e("AudioClassifierViewModel", "AudioClassifierViewModel Error: $error")
            }
        }
        audioClassifierHelper.setListener(resultListener)
    }

    override fun onCleared() {
        super.onCleared()
        audioClassifierHelper.stopAudioClassification()
    }
}