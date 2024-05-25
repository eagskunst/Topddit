package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.domain.entity.ContentEntity

class ChildrenDataModelToContentMapper : Mapper<ChildrenDataModel, ContentEntity> {
    override suspend fun map(value: ChildrenDataModel): ContentEntity {
        return ContentEntity(
            selfText = value.selftext,
            imagesUrls = value.preview?.images?.firstOrNull()?.resolutions?.map { it.url },
            videoUrl = value.media?.redditVideo?.fallbackUrl,
        )
    }
}
