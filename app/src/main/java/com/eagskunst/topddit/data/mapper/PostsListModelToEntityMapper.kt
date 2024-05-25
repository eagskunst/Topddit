package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.data.model.PostsListModel
import com.eagskunst.topddit.domain.entity.ContentEntity
import com.eagskunst.topddit.domain.entity.PostEntity
import java.time.LocalDateTime
import java.time.ZoneOffset

class PostsListModelToEntityMapper(
    private val contentMapper: Mapper<ChildrenDataModel, ContentEntity>,
) : Mapper<PostsListModel, List<PostEntity>> {
    override suspend fun map(value: PostsListModel): List<PostEntity> {
        return value.posts.children.map { post ->
            post.info
        }.map { post ->
            PostEntity(
                id = post.id,
                title = post.title,
                subreddit = post.subredditNamePrefixed,
                author = post.author,
                upVotes = post.ups,
                commentsCount = post.numComments,
                createdAt = LocalDateTime.ofEpochSecond(post.createdUtc, 0, ZoneOffset.UTC),
                content = contentMapper.map(post),
            )
        }
    }
}
