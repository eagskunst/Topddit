package com.eagskunst.topddit.presentation.post.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eagskunst.topddit.R

@Composable
fun Post(
    post: PostViewState.Post,
    showDivider: Boolean = true,
    modifier: Modifier = Modifier,
    onPostClick: () -> Unit = {},
) {
    Column {
        Column(modifier = modifier) {
            PostTitle(
                subreddit = post.subredditName,
                humanCreatedDate = post.humanCreatedDate,
            )
            PostContent(title = post.title, modifier = Modifier.padding(vertical = 5.dp))
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
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = subreddit, modifier = Modifier)
            Spacer(Modifier.width(2.dp))
            Text(text = humanCreatedDate, modifier = Modifier)
        }
    }
}

@Composable
fun PostContent(
    modifier: Modifier = Modifier,
    title: String,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun PostInteractions(
    modifier: Modifier = Modifier,
    upVotes: String,
    commentsCount: String,
) {
    Row(modifier = modifier) {
        PostInteraction(
            image = Icons.Filled.ThumbUp,
            interactions = upVotes,
        )
        Spacer(Modifier.width(10.dp))
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
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.size(15.dp),
        )
        Spacer(Modifier.width(2.dp))
        Text(text = interactions, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPost() {
    val post =
        PostViewState.Post(
            id = "1",
            title = "The fact that Nate Knows the vaul Tec salesman proves he's not a synth",
            subredditName = "r/Fallout",
            authorName = "u/Living_Disk_9345",
            upVotesRelativeCount = "4.6k",
            commentsRelativeCount = "45",
            humanCreatedDate = "1 hour ago",
        )
    Post(
        post = post,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
    )
}
