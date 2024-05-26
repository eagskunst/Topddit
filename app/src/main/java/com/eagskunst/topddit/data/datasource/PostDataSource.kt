package com.eagskunst.topddit.data.datasource

import com.eagskunst.topddit.common.DataResult
import com.eagskunst.topddit.common.ErrorResult
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.common.Success
import com.eagskunst.topddit.common.thread.Asyncable
import com.eagskunst.topddit.data.model.PostsListModel
import com.eagskunst.topddit.data.service.PostsService
import com.eagskunst.topddit.domain.entity.PostEntity
import kotlinx.coroutines.delay
import timber.log.Timber

class PostDataSource(
    private val service: PostsService,
    private val postListMapper: Mapper<PostsListModel, List<PostEntity>>,
    private val postDetailMapper: Mapper<List<PostsListModel>, PostEntity>,
) : Asyncable {
    suspend fun getPosts(lastPostId: String?): DataResult<List<PostEntity>> {
        delay(3500)
        val actualLastPostId =
            if (lastPostId != null) {
                "t3_$lastPostId"
            } else {
                null
            }
        Timber.d("Getting top posts - lastPostId: $actualLastPostId")
        return when (val posts = runSafely { service.getTopPosts(lastPostId = actualLastPostId) }) {
            is ErrorResult -> ErrorResult(posts.throwable, posts.errorInfo)
            is Success -> Success(postListMapper.map(posts.data))
        }
    }

    suspend fun getPostDetail(
        subredditId: String,
        postId: String,
    ): DataResult<PostEntity> {
        Timber.d("Getting post detail of $postId from r/$subredditId")
        return when (
            val postDetail =
                runSafely {
                    service.getPostDetail(
                        subreddit = "r/$subredditId",
                        postId = postId,
                    )
                }
        ) {
            is ErrorResult -> ErrorResult(postDetail.throwable, postDetail.errorInfo)
            is Success -> Success(postDetailMapper.map(postDetail.data))
        }
    }
}
