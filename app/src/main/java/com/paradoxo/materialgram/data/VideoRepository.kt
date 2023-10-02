package com.paradoxo.materialgram.data

import com.paradoxo.materialgram.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface VideoRepository {
    suspend fun getVideos(): Flow<List<Video>>
}

class VideoRepositoryImpl @Inject constructor(
    private val videoLocalDataSource: VideoLocalDataSource,
    private val videoService: VideoService
) : VideoRepository {
    override suspend fun getVideos(): Flow<List<Video>> {

        return flow {
            val localVideos = videoLocalDataSource.videos

            if (localVideos.isNotEmpty()) {
                emit(localVideos)
            }

            val remoteVideos = videoService.getVideos()
            emit(localVideos + remoteVideos)
        }
    }
}