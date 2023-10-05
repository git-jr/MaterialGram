package com.paradoxo.materialgram.domain

import com.paradoxo.materialgram.data.VideoRepository
import com.paradoxo.materialgram.domain.model.Reels
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface VideoUseCase {
    suspend fun getVideos(): Flow<List<Reels>>
}

class VideoUseCaseImpl @Inject constructor(
    private val repository: VideoRepository
) : VideoUseCase {
    override suspend fun getVideos(): Flow<List<Reels>> {
        return repository.getVideos()
    }

}