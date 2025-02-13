package com.eagskunst.topddit.presentation.mapper

import android.icu.text.CompactDecimalFormat
import android.text.format.DateUtils
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.domain.entity.CommentEntity
import com.eagskunst.topddit.domain.entity.ContentEntity
import com.eagskunst.topddit.domain.entity.PostEntity
import com.eagskunst.topddit.domain.entity.SubredditEntity
import com.eagskunst.topddit.presentation.post.list.Comment
import com.eagskunst.topddit.presentation.post.list.Content
import com.eagskunst.topddit.presentation.post.list.Post
import com.eagskunst.topddit.presentation.post.list.Subreddit
import java.util.Locale

class PostEntityToPostMapper(
    private val contentMapper: Mapper<ContentEntity, Content>,
    private val subredditMapper: Mapper<SubredditEntity, Subreddit>,
    private val commentMapper: Mapper<CommentEntity, Comment>,
) : Mapper<PostEntity, Post> {
    private val compactDecimalFormat =
        CompactDecimalFormat.getInstance(
            Locale.getDefault(),
            CompactDecimalFormat.CompactStyle.SHORT,
        )

    override suspend fun map(value: PostEntity): Post {
        return value.run {
            Post(
                id = id,
                title = title,
                authorName = author,
                upVotesRelativeCount = compactDecimalFormat.format(upVotes),
                commentsRelativeCount = compactDecimalFormat.format(commentsCount),
                humanCreatedDate =
                    DateUtils.getRelativeTimeSpanString(
                        createdAt.toInstant().toEpochMilli(),
                    ).toString(),
                content = content?.let { contentMapper.map(content) },
                subreddit = subreddit?.let { subredditMapper.map(it) },
                comments = comments.sortedByDescending { it.upVotes }.map { commentMapper.map(it) },
            )
        }
    }
}
