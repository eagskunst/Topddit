package com.eagskunst.topddit.presentation.post.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagskunst.topddit.common.ErrorResult
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.common.Success
import com.eagskunst.topddit.domain.entity.PostEntity
import com.eagskunst.topddit.domain.interactor.GetPosts
import kotlinx.coroutines.launch
import timber.log.Timber

class PostListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPosts: GetPosts,
    private val mapper: Mapper<PostEntity, Post>,
) : ViewModel() {
    private val _posts = MutableLiveData<PostViewState>(PostViewState.Loading())
    val posts = _posts as LiveData<PostViewState>

    fun getTopPosts() {
        viewModelScope.launch {
            when (val posts = getPosts(savedStateHandle["lastPostId"])) {
                is ErrorResult -> postErrorResult(posts)
                is Success -> {
                    val newPosts = posts.data.map { mapper.map(it) }
                    addAndPostNewPosts(newPosts)
                }
            }
        }
    }

    private fun addAndPostNewPosts(newPosts: List<Post>) {
        val loadedPosts = getLoadedPosts()
        _posts.value = PostViewState.Posts(loadedPosts + newPosts)
        savedStateHandle["lastPostId"] = newPosts.lastOrNull()?.id
        Timber.d("Posts Result: ${posts.value}")
    }

    private fun getLoadedPosts(): List<Post> {
        val loadedPosts =
            when (val currentState = _posts.value) {
                is PostViewState.GeneralError -> currentState.posts ?: listOf()
                is PostViewState.Loading -> currentState.posts ?: listOf()
                is PostViewState.Posts -> currentState.posts
                null -> listOf()
            }
        return loadedPosts
    }

    private fun postErrorResult(postsErrorResult: ErrorResult<List<PostEntity>>) {
        val loadedPosts = getLoadedPosts()
        _posts.value = PostViewState.GeneralError(postsErrorResult.throwable, loadedPosts)
        Timber.d("Posts result: ${posts.value}")
    }
}
