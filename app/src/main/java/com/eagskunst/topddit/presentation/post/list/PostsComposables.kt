package com.eagskunst.topddit.presentation.post.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eagskunst.topddit.R

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
    onPostClick: () -> Unit = {},
) {
    Column(modifier = Modifier.clickable(onClick = onPostClick)) {
        Column(modifier = modifier) {
            PostTitle(
                subreddit = post.subreddit?.name ?: "",
                iconUrl = post.subreddit?.icon,
                humanCreatedDate = post.humanCreatedDate,
            )
            PostContent(
                title = post.title,
                content = post.content,
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
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
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
            Text(text = humanCreatedDate, fontSize = 12.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun PostContent(
    title: String,
    content: Content?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(5.dp))
        if (content != null) {
            when (content.type) {
                PostType.TEXT -> Spacer(Modifier)
                PostType.VIDEO, PostType.IMAGE ->
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
                                .height(150.dp)
                                .clip(AbsoluteRoundedCornerShape(15.dp)),
                    )
            }
        }
    }
}

@Composable
fun PostInteractions(
    modifier: Modifier = Modifier,
    upVotes: String,
    commentsCount: String,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        PostInteraction(
            image = Icons.Filled.ThumbUp,
            interactions = upVotes,
        )
        PostInteraction(
            image = ImageVector.vectorResource(id = R.drawable.ic_comment),
            interactions = commentsCount,
        )
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
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.width(2.dp))
        Text(text = interactions, modifier = Modifier)
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
        )
    Post(
        post = post,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
    )
}
