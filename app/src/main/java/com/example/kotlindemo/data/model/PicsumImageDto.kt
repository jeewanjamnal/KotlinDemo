package com.example.kotlindemo.data.model

import kotlinx.serialization.SerialName

data class PicsumImageDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerialName("download_url")
    val downloadUrl: String
)
