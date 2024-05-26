package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.data.model.PostsListModel
import com.eagskunst.topddit.domain.entity.CommentEntity
import com.eagskunst.topddit.domain.entity.PostEntity
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PostListToEntityWithCommentsMapper(
    private val postMapper: Mapper<PostsListModel, List<PostEntity>>,
    private val commentsMapper: Mapper<ChildrenDataModel, CommentEntity>,
) : Mapper<List<PostsListModel>, PostEntity> {
    override suspend fun map(value: List<PostsListModel>): PostEntity {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Posts list is empty")
        }
        val post = postMapper.map(value.first())[0]
        val comments =
            value.asFlow()
                .drop(1)
                .map { it.posts.children }
                .map { childrens -> childrens.map { it.info } }
                .map { childrensData ->
                    childrensData.map { commentsMapper.map(it) }
                }
        return post.copy(comments = comments.first())
    }
}
