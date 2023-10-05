package com.paradoxo.materialgram.data.network

import com.paradoxo.materialgram.data.model.VideoResponse
import retrofit2.http.GET

interface VideoService {

    @GET("reels.json") // ".json" in the and because api is a mock from github pages
    suspend fun getVideos(): List<VideoResponse>
}
