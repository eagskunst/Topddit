package com.eagskunst.topddit.domain.entity

import java.time.LocalDateTime

data class PostEntity(
    val id: String,
    val title: String,
    val subreddit: String,
    val author: String,
    val upVotes: Int,
    val commentsCount: Int,
    val createdAt: LocalDateTime,
)
