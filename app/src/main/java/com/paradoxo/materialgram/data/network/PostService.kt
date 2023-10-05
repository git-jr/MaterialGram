package com.paradoxo.materialgram.data.network

import com.paradoxo.materialgram.data.model.PostResponse
import retrofit2.http.GET

interface PostService {

    @GET("posts.json") // ".json" in the and because api is a mock from github pages
    suspend fun getPosts(): List<PostResponse>
}
