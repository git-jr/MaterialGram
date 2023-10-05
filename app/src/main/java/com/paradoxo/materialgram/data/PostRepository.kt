package com.paradoxo.materialgram.data

import com.paradoxo.materialgram.data.local.PostLocalDataSource
import com.paradoxo.materialgram.data.network.PostService
import com.paradoxo.materialgram.domain.model.Post
import com.paradoxo.materialgram.domain.toPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PostRepository {
    suspend fun getPosts(): Flow<List<Post>>
}

class PostRepositoryImpl @Inject constructor(
    private val postLocalDataSource: PostLocalDataSource,
    private val postService: PostService
) : PostRepository {
    override suspend fun getPosts(): Flow<List<Post>> {

        return flow {
            val localPosts = postLocalDataSource.posts

            if (localPosts.isNotEmpty()) {
                emit(localPosts)
            }

            val remotePosts = postService.getPosts().map { it.toPost() }
            emit(localPosts + remotePosts)
        }
    }
}