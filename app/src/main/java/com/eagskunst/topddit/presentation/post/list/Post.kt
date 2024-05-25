package com.eagskunst.topddit.presentation.post.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String,
    val title: String,
    val authorName: String,
    val upVotesRelativeCount: String,
    val commentsRelativeCount: String,
    val humanCreatedDate: String,
    val subreddit: Subreddit?,
    val content: Content?,
) : Parcelable

@Parcelize
data class Subreddit(
    val name: String,
    val icon: String,
) : Parcelable

enum class PostType {
    TEXT,
    VIDEO,
    IMAGE,
}

@Parcelize
data class Content(
    val selfText: String?,
    val imagesUrl: List<String?>?,
    val videoUrl: String?,
) : Parcelable {
    val type: PostType
        get() {
            return if (videoUrl != null) {
                PostType.VIDEO
            } else if (imagesUrl != null) {
                PostType.IMAGE
            } else {
                PostType.TEXT
            }
        }
}
