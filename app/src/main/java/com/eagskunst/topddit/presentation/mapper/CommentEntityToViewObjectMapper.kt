package com.eagskunst.topddit.presentation.mapper

import android.icu.text.CompactDecimalFormat
import android.text.format.DateUtils
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.domain.entity.CommentEntity
import com.eagskunst.topddit.domain.entity.ContentEntity
import com.eagskunst.topddit.presentation.post.list.Comment
import com.eagskunst.topddit.presentation.post.list.Content
import java.util.Locale

class CommentEntityToViewObjectMapper(
    private val contentMapper: Mapper<ContentEntity, Content>,
) : Mapper<CommentEntity, Comment> {
    private val compactDecimalFormat =
        CompactDecimalFormat.getInstance(
            Locale.getDefault(),
            CompactDecimalFormat.CompactStyle.SHORT,
        )

    override suspend fun map(value: CommentEntity): Comment {
        return value.run {
            Comment(
                id = id,
                authorName = author,
                upVotesRelativeCount = compactDecimalFormat.format(upVotes),
                humanCreatedDate =
                    DateUtils.getRelativeTimeSpanString(
                        createdAt.toInstant().toEpochMilli(),
                    ).toString(),
                content = content?.let { contentMapper.map(content) },
            )
        }
    }
}
