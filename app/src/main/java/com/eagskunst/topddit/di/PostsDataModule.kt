package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.datasource.PostDataSource
import com.eagskunst.topddit.domain.interactor.GetPostDetail
import com.eagskunst.topddit.domain.interactor.GetPosts

class PostsDataModule(
    serviceModule: ServiceModule,
    mapperModule: MapperModule,
    threadModule: ThreadModule,
) {
    private val postDataSource =
        PostDataSource(
            service = serviceModule.postService,
            postListMapper = mapperModule.postsListModelToEntityMapper,
            postDetailMapper = mapperModule.postListToEntityWithCommentsMapper,
        )

    val getPostsInteractor = GetPosts(postDataSource, threadModule.dispatchers)

    val getPostDetailInteractor = GetPostDetail(postDataSource, threadModule.dispatchers)
}
