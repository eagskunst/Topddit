package com.eagskunst.topddit.domain.entity

import java.time.ZonedDateTime

data class CommentEntity(
    val id: String,
    val author: String,
    val upVotes: Int,
    val createdAt: ZonedDateTime,
    val content: ContentEntity?,
)
