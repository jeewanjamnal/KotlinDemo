package com.example.kotlindemo.data.remote

import com.example.kotlindemo.data.model.PicsumImageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumApi {
    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PicsumImageDto>
}