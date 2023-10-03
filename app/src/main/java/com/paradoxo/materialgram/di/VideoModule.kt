package com.paradoxo.materialgram.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.paradoxo.materialgram.data.VideoLocalDataSource
import com.paradoxo.materialgram.data.VideoRepository
import com.paradoxo.materialgram.data.VideoRepositoryImpl
import com.paradoxo.materialgram.data.VideoService
import com.paradoxo.materialgram.domain.VideoUseCase
import com.paradoxo.materialgram.domain.VideoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object VideoModule {

    @Provides
    fun provideVideoUseCase(videoRepository: VideoRepository): VideoUseCase {
        return VideoUseCaseImpl(videoRepository)
    }

    @Provides
    fun provideVideoRepository(
        videoLocalDataSource: VideoLocalDataSource,
        videoService: VideoService
    ): VideoRepository {
        return VideoRepositoryImpl(videoLocalDataSource, videoService)
    }

    @Provides
    fun provideVideoDataSource(): VideoLocalDataSource {
        return VideoLocalDataSource()
    }


    @Provides
    fun provideExpoVideoPlayer(application: Application): Player {
        return ExoPlayer.Builder(application)
            .build().apply {
                playWhenReady = true
                repeatMode = Player.REPEAT_MODE_ALL
            }
    }
}