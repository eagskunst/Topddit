package com.eagskunst.topddit.presentation.post.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eagskunst.topddit.R
import com.eagskunst.topddit.presentation.common.shimmerEffect

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    showAuthor: Boolean = false,
    showDivider: Boolean = true,
    multiContent: Boolean = false,
    onPostClick: (Post) -> Unit = {},
) {
    Column(modifier = Modifier.clickable(onClick = { onPostClick(post) })) {
        Column(modifier = modifier) {
            PostTitle(
                subreddit = post.subreddit?.name ?: "",
                iconUrl = post.subreddit?.icon,
                humanCreatedDate = post.humanCreatedDate,
                authorName = if (showAuthor) post.authorName else null,
            )
            PostContent(
                title = post.title,
                content = post.content,
                multiContent = multiContent,
                modifier = Modifier.padding(vertical = 5.dp),
            )
            PostInteractions(
                upVotes = post.upVotesRelativeCount,
                commentsCount = post.commentsRelativeCount,
            )
        }
        if (showDivider) {
            Divider()
        }
    }
}

@Composable
fun PostTitle(
    subreddit: String,
    humanCreatedDate: String,
    iconUrl: String?,
    modifier: Modifier = Modifier,
    authorName: String? = null,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(iconUrl)
                        .crossfade(true)
                        .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "icon_subreddit_$subreddit",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(20.dp)
                        .clip(CircleShape),
            )
            Text(text = subreddit, fontSize = 12.sp)
            if (authorName == null) {
                Text(text = humanCreatedDate, fontSize = 12.sp, fontWeight = FontWeight.Light)
            }
        }
        if (authorName != null) {
            Text(
                text = "$authorName · $humanCreatedDate",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
            )
        }
    }
}

@Composable
fun PostContent(
    title: String,
    content: Content?,
    multiContent: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            maxLines = if (multiContent) Integer.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
        Spacer(Modifier.height(5.dp))
        if (content != null) {
            if (content.type in
                listOf(
                    PostType.VIDEO,
                    PostType.IMAGE,
                    PostType.TEXT_WITH_IMAGE,
                    PostType.TEXT_WITH_VIDEO,
                )
            ) {
                AsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(content.imagesUrl?.lastOrNull())
                            .crossfade(true)
                            .build(),
                    contentDescription = "image_for_post",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(max = if (!multiContent) 350.dp else Dp.Unspecified)
                            .clip(AbsoluteRoundedCornerShape(15.dp)),
                )
            }
            if (multiContent && content.type in
                listOf(
                    PostType.TEXT_WITH_IMAGE,
                    PostType.TEXT_WITH_VIDEO,
                    PostType.TEXT,
                ) && !content.selfText.isNullOrBlank()
            ) {
                Text(text = content.selfText, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun PostInteractions(
    modifier: Modifier = Modifier,
    upVotes: String,
    commentsCount: String? = null,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        PostInteraction(
            image = Icons.Filled.ThumbUp,
            interactions = upVotes,
        )
        if (commentsCount != null) {
            PostInteraction(
                image = ImageVector.vectorResource(id = R.drawable.ic_comment),
                interactions = commentsCount,
            )
        }
    }
}

@Composable
fun PostInteraction(
    image: ImageVector,
    interactions: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.size(15.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
        Spacer(Modifier.width(2.dp))
        Text(text = interactions, modifier = Modifier)
    }
}

@Composable
fun PostPlaceholder(
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
) {
    Column {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .shimmerEffect(),
                )
                Box(
                    modifier =
                        Modifier
                            .height(8.dp)
                            .width(60.dp)
                            .shimmerEffect(),
                )
                Box(
                    modifier =
                        Modifier
                            .height(8.dp)
                            .width(30.dp)
                            .shimmerEffect(),
                )
            }
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.95f)
                        .padding(vertical = 8.dp)
                        .height(10.dp)
                        .shimmerEffect(),
            )
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.6f)
                        .height(10.dp)
                        .shimmerEffect(),
            )
            Spacer(Modifier.height(8.dp))
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(AbsoluteRoundedCornerShape(15.dp))
                        .shimmerEffect(),
            )
        }
        if (showDivider) {
            Divider(modifier = Modifier.shimmerEffect())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPost() {
    val post =
        Post(
            id = "1",
            title = "The fact that Nate Knows the vaul Tec salesman proves he's not a synth",
            authorName = "u/Living_Disk_9345",
            upVotesRelativeCount = "4.6k",
            commentsRelativeCount = "45",
            humanCreatedDate = "1 hour ago",
            subreddit =
                Subreddit(
                    name = "r/Fallout",
                    icon = "https://b.thumbs.redditmedia.com/aToqW1aEyY9458lvNJfPu83chh05lOdLlnnNPgY2j4M.png",
                ),
            content =
                Content(
                    selfText = "you know",
                    imagesUrl = null,
                    videoUrl = null,
                ),
            comments = listOf(),
        )
    Post(
        post = post,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPostPlaceholder() {
    PostPlaceholder(
        modifier =
            Modifier.fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
    )
}
