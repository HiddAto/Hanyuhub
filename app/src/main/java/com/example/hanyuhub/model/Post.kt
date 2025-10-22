package com.example.hanyuhub.model

import kotlinx.serialization.Serializable

data class Post(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)

