package com.eagskunst.topddit.presentation.post.detail

import com.eagskunst.topddit.presentation.post.list.Post

sealed class PostDetailViewState {
    data object Loading : PostDetailViewState()

    data class Success(val post: Post) : PostDetailViewState()

    data class GeneralError(val error: Throwable) :
        PostDetailViewState()
}
