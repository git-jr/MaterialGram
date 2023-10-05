package com.paradoxo.materialgram.domain.model

data class User(
    val name: String,
    val avatar: String,
    val followers: Int,
    val following: Int,
    val posts: Int,
    val description: String,
    val website: String,
    val isFollowing: Boolean,
    val isMe: Boolean,
)
