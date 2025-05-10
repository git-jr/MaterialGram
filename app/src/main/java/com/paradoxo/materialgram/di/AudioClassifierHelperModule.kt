package com.paradoxo.materialgram.di

import android.content.Context
import com.paradoxo.materialgram.presentation.screens.voicedetection.AudioClassifierHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AudioClassifierHelperModule {
    @Provides
    fun provideAudioClassifierHelper(@ApplicationContext context: Context): AudioClassifierHelper {
        return AudioClassifierHelper(context)
    }
}