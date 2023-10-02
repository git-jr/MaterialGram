package com.paradoxo.materialgram.domain

import com.paradoxo.materialgram.data.VideoLocalDataSource
import com.paradoxo.materialgram.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface VideoRepository {
    suspend fun getVideos(): Flow<List<Video>>
}

class VideoRepositoryImpl @Inject constructor(
    private val videoLocalDataSource: VideoLocalDataSource
) : VideoRepository {
    override suspend fun getVideos(): Flow<List<Video>> {
        return flowOf(videoLocalDataSource.videos)
    }
}