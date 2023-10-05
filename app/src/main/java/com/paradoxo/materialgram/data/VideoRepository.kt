package com.paradoxo.materialgram.data

import com.paradoxo.materialgram.data.local.VideoLocalDataSource
import com.paradoxo.materialgram.data.network.VideoService
import com.paradoxo.materialgram.domain.model.Reels
import com.paradoxo.materialgram.domain.toReels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface VideoRepository {
    suspend fun getVideos(): Flow<List<Reels>>
}

class VideoRepositoryImpl @Inject constructor(
    private val videoLocalDataSource: VideoLocalDataSource,
    private val videoService: VideoService
) : VideoRepository {
    override suspend fun getVideos(): Flow<List<Reels>> {

        return flow {
            val localVideos = videoLocalDataSource.reels

            if (localVideos.isNotEmpty()) {
                emit(localVideos)
            }

            val remoteVideos = videoService.getVideos().map { it.toReels() }
            emit(localVideos + remoteVideos)
        }
    }
}