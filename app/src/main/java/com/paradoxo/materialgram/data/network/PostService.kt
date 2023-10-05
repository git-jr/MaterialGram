package com.paradoxo.materialgram.data.network

import com.paradoxo.materialgram.domain.model.Post
import retrofit2.http.GET

interface PostService {

    @GET("posts.json")
    suspend fun getPosts(): List<Post>
}
