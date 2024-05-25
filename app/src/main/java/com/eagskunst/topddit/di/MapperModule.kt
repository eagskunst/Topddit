package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.mapper.ChildrenDataModelToContentMapper
import com.eagskunst.topddit.data.mapper.PostsListModelToEntityMapper
import com.eagskunst.topddit.data.mapper.SubredditDetailModelToEntityMapper
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
    val postEntityToPostMapper =
        PostEntityToPostMapper(contentEntityToViewObjectMapper, subredditEntityToViewObjectMapper)
}
