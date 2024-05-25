package com.eagskunst.topddit.presentation.mapper

import android.icu.text.CompactDecimalFormat
import android.text.format.DateUtils
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.domain.entity.PostEntity
import com.eagskunst.topddit.presentation.post.list.PostViewState
import java.util.Locale

class PostEntityToPostMapper : Mapper<PostEntity, PostViewState.Post> {
    private val compactDecimalFormat =
        CompactDecimalFormat.getInstance(
            Locale.getDefault(),
            CompactDecimalFormat.CompactStyle.SHORT,
        )

    override suspend fun map(value: PostEntity): PostViewState.Post {
        return value.run {
            PostViewState.Post(
                id = id,
                title = title,
                subredditName = subreddit,
                authorName = author,
                upVotesRelativeCount = compactDecimalFormat.format(upVotes),
                commentsRelativeCount = compactDecimalFormat.format(commentsCount),
                humanCreatedDate =
                    DateUtils.getRelativeTimeSpanString(
                        createdAt.toInstant().toEpochMilli(),
                    ).toString(),
            )
        }
    }
}
