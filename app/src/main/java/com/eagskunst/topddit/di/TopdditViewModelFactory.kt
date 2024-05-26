package com.eagskunst.topddit.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import com.eagskunst.topddit.presentation.post.detail.PostDetailViewModel
import com.eagskunst.topddit.presentation.post.list.PostListViewModel

@Suppress("UNCHECKED_CAST")
class TopdditViewModelFactory(
    private val postsDataModule: PostsDataModule,
    private val mapperModule: MapperModule,
    owner: SavedStateRegistryOwner,
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        val viewModel =
            when (modelClass) {
                PostListViewModel::class.java -> createPostListViewModel(handle)
                PostDetailViewModel::class.java -> createPostDetailViewModel(handle)
                else -> throw IllegalArgumentException("${modelClass.name} is not supported")
            }
        return viewModel as T
    }

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        val viewModel =
            when (modelClass) {
                PostListViewModel::class.java -> createPostListViewModel(extras.createSavedStateHandle())
                PostDetailViewModel::class.java -> createPostDetailViewModel(extras.createSavedStateHandle())
                else -> throw IllegalArgumentException("${modelClass.name} is not supported")
            }
        return viewModel as T
    }

    private fun createPostDetailViewModel(handle: SavedStateHandle): PostDetailViewModel {
        return PostDetailViewModel(
            savedStateHandle = handle,
            getPostDetail = postsDataModule.getPostDetailInteractor,
            mapper = mapperModule.postEntityToPostMapper,
        )
    }

    private fun createPostListViewModel(handle: SavedStateHandle): PostListViewModel {
        return PostListViewModel(
            savedStateHandle = handle,
            getPosts = postsDataModule.getPostsInteractor,
            mapper = mapperModule.postEntityToPostMapper,
        )
    }
}
