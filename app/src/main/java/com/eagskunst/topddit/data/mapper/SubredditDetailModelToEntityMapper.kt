package com.eagskunst.topddit.data.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.data.model.SubredditDetailModel
import com.eagskunst.topddit.domain.entity.SubredditEntity

class SubredditDetailModelToEntityMapper : Mapper<SubredditDetailModel, SubredditEntity> {
    override suspend fun map(value: SubredditDetailModel): SubredditEntity {
        return SubredditEntity(
            name = value.displayNamePrefixed ?: "",
            iconUrl = value.communityIcon?.split('?')?.firstOrNull() ?: "",
        )
    }
}
