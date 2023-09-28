package com.paradoxo.materialgram.domain

import com.paradoxo.materialgram.data.PostRepository
import com.paradoxo.materialgram.domain.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostUseCase {
    suspend fun getPosts(): Flow<List<Post>>
}

class PostUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : PostUseCase {
    override suspend fun getPosts(): Flow<List<Post>> {
        return postRepository.getPosts()
    }
}
