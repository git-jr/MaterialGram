package com.paradoxo.materialgram.data

import com.paradoxo.materialgram.domain.model.Video
import retrofit2.http.GET

interface VideoService {

    @GET("videos")
    suspend fun getVideos(): List<Video>
}
