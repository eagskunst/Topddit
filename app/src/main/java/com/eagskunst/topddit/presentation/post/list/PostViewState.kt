package com.eagskunst.topddit.presentation.post.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PostViewState {
    data class Loading(val posts: List<Post>? = null) : PostViewState()

    data class Posts(val posts: List<Post>) : PostViewState()

    @Parcelize
    data class Post(
        val id: String,
        val title: String,
        val subredditName: String,
        val authorName: String,
        val upVotesRelativeCount: String,
        val commentsRelativeCount: String,
        val humanCreatedDate: String,
    ) : Parcelable

    data class GeneralError(val error: Throwable, val posts: List<Post>? = null) :
        PostViewState()
}
