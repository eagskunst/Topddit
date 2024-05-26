package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.mapper.ChildrenDataModelToCommentEntityMapper
import com.eagskunst.topddit.data.mapper.ChildrenDataModelToContentMapper
import com.eagskunst.topddit.data.mapper.PostListToEntityWithCommentsMapper
import com.eagskunst.topddit.data.mapper.PostsListModelToEntityMapper
import com.eagskunst.topddit.data.mapper.SubredditDetailModelToEntityMapper
import com.eagskunst.topddit.presentation.mapper.CommentEntityToViewObjectMapper
import com.eagskunst.topddit.presentation.mapper.ContentEntityToViewObjectMapper
import com.eagskunst.topddit.presentation.mapper.PostEntityToPostMapper
import com.eagskunst.topddit.presentation.mapper.SubredditEntityToViewObjectMapper

class MapperModule {
    private val childrenDataModelToContentMapper = ChildrenDataModelToContentMapper()
    private val subredditDetailModelToEntityMapper = SubredditDetailModelToEntityMapper()
    val postsListModelToEntityMapper =
        PostsListModelToEntityMapper(
            childrenDataModelToContentMapper,
            subredditDetailModelToEntityMapper,
        )
    private val contentEntityToViewObjectMapper = ContentEntityToViewObjectMapper()
    private val subredditEntityToViewObjectMapper = SubredditEntityToViewObjectMapper()
    private val commentEntityToViewObjectMapper =
        CommentEntityToViewObjectMapper(
            contentEntityToViewObjectMapper,
        )
    val postEntityToPostMapper =
        PostEntityToPostMapper(
            contentEntityToViewObjectMapper,
            subredditEntityToViewObjectMapper,
            commentEntityToViewObjectMapper,
        )
    private val childrenDataModelToCommentEntityMapper = ChildrenDataModelToCommentEntityMapper()
    val postListToEntityWithCommentsMapper =
        PostListToEntityWithCommentsMapper(
            postsListModelToEntityMapper,
            childrenDataModelToCommentEntityMapper,
        )
}
