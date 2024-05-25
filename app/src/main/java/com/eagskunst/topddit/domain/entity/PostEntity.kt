package com.eagskunst.topddit.domain.entity

import java.time.ZonedDateTime

data class PostEntity(
    val id: String,
    val title: String,
    val author: String,
    val upVotes: Int,
    val commentsCount: Int,
    val createdAt: ZonedDateTime,
    val content: ContentEntity?,
    val subreddit: SubredditEntity?,
)
