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
    companion object {
        private const val LAST_POST_ID_KEY = "lastPostId"
        private const val POSTS_KEY = "posts"
    }

    private val _posts = MutableLiveData<PostViewState>(PostViewState.Loading())
    val posts = _posts as LiveData<PostViewState>

    fun getTopPosts() {
        val savedPosts = savedStateHandle.get<List<Post>>(POSTS_KEY)
        if (savedPosts != null) {
            addAndPostNewPosts(savedPosts)
            return
        }
        getPostsAsync()
    }

    private fun getPostsAsync() {
        _posts.value = PostViewState.Loading(getLoadedPosts())
        viewModelScope.launch {
            when (val posts = getPosts(savedStateHandle[LAST_POST_ID_KEY])) {
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
        val actualNewPosts = newPosts.filter { !loadedPosts.contains(it) }
        _posts.value = PostViewState.Posts(loadedPosts + actualNewPosts)
        savedStateHandle[LAST_POST_ID_KEY] = newPosts.lastOrNull()?.id
        savedStateHandle[POSTS_KEY] = loadedPosts + actualNewPosts
        Timber.d("Posts Result: ${posts.value}")
    }

    private fun getLoadedPosts(): List<Post> {
        return savedStateHandle[POSTS_KEY] ?: listOf()
    }

    private fun postErrorResult(postsErrorResult: ErrorResult<List<PostEntity>>) {
        val loadedPosts = getLoadedPosts()
        _posts.value = PostViewState.GeneralError(postsErrorResult.throwable, loadedPosts)
        Timber.d("Posts result: ${posts.value}")
    }

    fun getTopPosts(reachedBottom: Boolean) {
        val currentState = _posts.value
        if (currentState is PostViewState.Loading || !reachedBottom) {
            return
        }
        getPostsAsync()
    }
}
