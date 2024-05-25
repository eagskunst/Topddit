package com.eagskunst.topddit.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import com.eagskunst.topddit.domain.interactor.GetPosts
import com.eagskunst.topddit.presentation.mapper.PostEntityToPostMapper
import com.eagskunst.topddit.presentation.post.list.PostListViewModel

class PostsListPresentationModule(
    private val postsDataModule: PostsDataModule,
    private val mapperModule: MapperModule,
) {
    @Suppress("UNCHECKED_CAST")
    class PostListViewModelFactory(
        private val getPosts: GetPosts,
        private val mapper: PostEntityToPostMapper,
        owner: SavedStateRegistryOwner,
    ) : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            return PostListViewModel(
                savedStateHandle = handle,
                getPosts = getPosts,
                mapper = mapper,
            ) as T
        }

        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            return PostListViewModel(extras.createSavedStateHandle(), getPosts, mapper) as T
        }
    }

    fun createViewModelFactory(owner: SavedStateRegistryOwner): PostListViewModelFactory {
        return PostListViewModelFactory(
            getPosts = postsDataModule.getPostsInteractor,
            mapper = mapperModule.postEntityToPostMapper,
            owner = owner,
        )
    }
}
