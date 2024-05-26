package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.common.extensions.toZonedDateTime
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.domain.entity.CommentEntity
import com.eagskunst.topddit.domain.entity.ContentEntity

class ChildrenDataModelToCommentEntityMapper : Mapper<ChildrenDataModel, CommentEntity> {
    override suspend fun map(value: ChildrenDataModel): CommentEntity {
        return value.run {
            CommentEntity(
                id = id,
                author = author ?: "Unknown",
                content = ContentEntity(selfText = body, imagesUrls = null, videoUrl = null),
                createdAt = createdUtc.toZonedDateTime(),
                upVotes = ups ?: 0,
            )
        }
    }
}
