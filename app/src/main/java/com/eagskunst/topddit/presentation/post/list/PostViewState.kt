package com.eagskunst.topddit.presentation.post.list

sealed class PostViewState {
    data class Loading(val posts: List<Post>? = null) : PostViewState()

    data class Posts(val posts: List<Post>) : PostViewState()

    data class GeneralError(val error: Throwable, val posts: List<Post>? = null) :
        PostViewState()
}
