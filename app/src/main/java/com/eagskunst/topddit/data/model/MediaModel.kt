package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaModel(
    @SerialName("reddit_video")
    val redditVideo: RedditVideoModel? = null,
)
