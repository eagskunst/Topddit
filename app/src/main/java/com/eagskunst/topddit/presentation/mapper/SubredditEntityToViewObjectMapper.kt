package com.eagskunst.topddit.presentation.mapper

import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.domain.entity.SubredditEntity
import com.eagskunst.topddit.presentation.post.list.Subreddit

class SubredditEntityToViewObjectMapper : Mapper<SubredditEntity, Subreddit> {
    override suspend fun map(value: SubredditEntity): Subreddit {
        return value.run {
            Subreddit(
                name = name,
                icon = iconUrl,
            )
        }
    }
}
