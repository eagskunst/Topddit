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
    val comments: List<Comment>,
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
    TEXT_WITH_IMAGE,
    TEXT_WITH_VIDEO,
}

@Parcelize
data class Content(
    val selfText: String?,
    val imagesUrl: List<String?>?,
    val videoUrl: String?,
) : Parcelable {
    val type: PostType
        get() {
            return if (!selfText.isNullOrBlank() && videoUrl != null) {
                PostType.TEXT_WITH_VIDEO
            } else if (!selfText.isNullOrBlank() && !imagesUrl.isNullOrEmpty()) {
                PostType.TEXT_WITH_IMAGE
            } else if (videoUrl != null) {
                PostType.VIDEO
            } else if (!imagesUrl.isNullOrEmpty()) {
                PostType.IMAGE
            } else {
                PostType.TEXT
            }
        }
}

@Parcelize
data class Comment(
    val id: String,
    val authorName: String,
    val upVotesRelativeCount: String,
    val humanCreatedDate: String,
    val content: Content?,
) : Parcelable
