package com.paradoxo.materialgram.presentation.screens.voicedetection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mediapipe.tasks.components.containers.Category
import com.paradoxo.materialgram.presentation.screens.voicedetection.AudioClassifierHelper.ResultBundle
import com.paradoxo.materialgram.utils.PermissionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioClassifierViewModel @Inject constructor(
    private val audioClassifierHelper: AudioClassifierHelper,
    private val permissionUtils: PermissionUtils,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AudioClassifierUiState())
    var uiState = _uiState.asStateFlow()

    var netflixDetected = false
    var palmeirasDetected = false
    var coffeeDetected = false

    init {
        _uiState.value = _uiState.value.copy(
            micPermissionGranted = permissionUtils.microphonePermissionsGranted()
        )
        if (_uiState.value.micPermissionGranted) {
            startClassification()
        } else {
            requestMicPermission()
        }
    }


    fun startClassification() {
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

                            val detectionThresholds = mapOf(
                                "Palmeiras" to 0.75f,
                                "Coffe" to 0.75f,
                                "Netflix" to 0.75f
                            )

                            val detectedStates =
                                detectionThresholds.map { (categoryName, threshold) ->
                                    val category = categoryList.find {
                                        it.categoryName().contains(categoryName)
                                    }
                                    categoryName to (category != null && category.score() > threshold)
                                }.toMap()

                            _uiState.value = _uiState.value.copy(
                                palmeirasDetected = detectedStates["Palmeiras"] == true && !coffeeDetected,
                                coffeeDetected = detectedStates["Coffe"] == true && !netflixDetected,
                                netflixDetected = detectedStates["Netflix"] == true && !palmeirasDetected,
                            )

//                            Se for monitorar uma palavra específica, descomente o código abaixo:
//                            palmeirasDetected = detectedStates["Palmeiras"] == true
//                            coffeeDetected = detectedStates["Coffe"] == true
//                            netflixDetected = detectedStates["Netflix"] == true
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

    fun setActive(active: Boolean, onResult : (Boolean) -> Unit) {
        _uiState.value = _uiState.value.copy(
            active = active,
        )
        onResult(active)
    }

    override fun onCleared() {
        super.onCleared()
        audioClassifierHelper.stopAudioClassification()
    }

    fun setPermissionGranted(granted: Boolean) {
        _uiState.value = _uiState.value.copy(
            micPermissionGranted = granted
        )

        if (granted) {
            startClassification()
        } else {
            requestMicPermission()
        }
    }

    fun requestMicPermission() {
        _uiState.value = _uiState.value.copy(
            requestMicPermission = true
        )
    }
}