package com.eagskunst.topddit.presentation.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.domain.entity.ContentEntity
import com.eagskunst.topddit.presentation.post.list.Content

class ContentEntityToViewObjectMapper : Mapper<ContentEntity, Content> {
    override suspend fun map(value: ContentEntity): Content {
        return value.run {
            Content(
                selfText = value.selfText,
                imagesUrl = imagesUrls,
                videoUrl = videoUrl,
            )
        }
    }
}
