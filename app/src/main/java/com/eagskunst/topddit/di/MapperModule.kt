package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.mapper.ChildrenDataModelToContentMapper
import com.eagskunst.topddit.data.mapper.PostsListModelToEntityMapper

class MapperModule {
    private val childrenDataModelToContentMapper = ChildrenDataModelToContentMapper()
    val postsListModelToEntityMapper =
        PostsListModelToEntityMapper(childrenDataModelToContentMapper)
}
