package com.example.kotlindemo.data.repository

import com.example.kotlindemo.data.model.PicsumImageDto
import com.example.kotlindemo.data.remote.PicsumApi
import jakarta.inject.Inject

class PicsumRepository @Inject constructor(private val api: PicsumApi) {
    suspend fun getPosts(page: Int, limit: Int, ): List<PicsumImageDto> {
        return api.getPhotos(page, limit);
    }
}