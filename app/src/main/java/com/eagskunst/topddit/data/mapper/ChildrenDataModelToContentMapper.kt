package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.data.model.ChildrenDataModel
import com.eagskunst.topddit.domain.entity.ContentEntity

class ChildrenDataModelToContentMapper : Mapper<ChildrenDataModel, ContentEntity> {
    override suspend fun map(value: ChildrenDataModel): ContentEntity {
        val previewImages = value.preview?.images?.firstOrNull()
        val images =
            previewImages?.resolutions?.map { it.url }
                ?.filter { it.contains("https://", ignoreCase = true) }
                // or
                ?: value.mediaMetadata?.map { (_, v) ->
                    v?.p?.lastOrNull()?.u ?: ""
                }?.filter { it.contains("https://", ignoreCase = true) }

        return ContentEntity(
            selfText = value.selftext,
            imagesUrls = images,
            videoUrl = value.media?.redditVideo?.fallbackUrl,
        )
    }
}
