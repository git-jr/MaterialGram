package com.paradoxo.materialgram.data.network

import com.paradoxo.materialgram.domain.model.Reels
import retrofit2.http.GET

interface VideoService {

    @GET("reels.json")
    suspend fun getVideos(): List<Reels>
}
