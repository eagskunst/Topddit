package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.common.extensions.toZonedDateTime
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.data.model.PostsListModel
import com.eagskunst.topddit.data.model.SubredditDetailModel
import com.eagskunst.topddit.domain.entity.ContentEntity
import com.eagskunst.topddit.domain.entity.PostEntity
import com.eagskunst.topddit.domain.entity.SubredditEntity
import kotlinx.serialization.json.JsonNull.content

class PostsListModelToEntityMapper(
    private val contentMapper: Mapper<ChildrenDataModel, ContentEntity>,
    private val subredditsMapper: Mapper<SubredditDetailModel, SubredditEntity>,
) : Mapper<PostsListModel, List<PostEntity>> {
    override suspend fun map(value: PostsListModel): List<PostEntity> {
        return value.posts.children.map { post ->
            post.info
        }.map { post ->
            PostEntity(
                id = post.id,
                title = post.title ?: "",
                author = post.author ?: "",
                upVotes = post.ups ?: 0,
                commentsCount = post.numComments ?: 0,
                createdAt = post.createdUtc.toZonedDateTime(),
                content = contentMapper.map(post),
                subreddit = post.subredditDetail?.let { subredditsMapper.map(it) },
            )
        }
    }
}
