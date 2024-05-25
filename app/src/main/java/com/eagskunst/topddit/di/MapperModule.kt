package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.mapper.ChildrenDataModelToContentMapper
import com.eagskunst.topddit.data.mapper.PostsListModelToEntityMapper
import com.eagskunst.topddit.presentation.mapper.PostEntityToPostMapper

class MapperModule {
    private val childrenDataModelToContentMapper = ChildrenDataModelToContentMapper()
    val postsListModelToEntityMapper =
        PostsListModelToEntityMapper(childrenDataModelToContentMapper)
    val postEntityToPostMapper = PostEntityToPostMapper()
}
