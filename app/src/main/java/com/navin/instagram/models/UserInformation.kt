package com.navin.instagram.models

data class UserInformation(
    val bio: String,
    val email: String,
    val id: String,
    val image: String,
    val posts_count: Int,
    val username: String,
    val website: String
)