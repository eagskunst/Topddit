package com.eagskunst.topddit.presentation.post.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eagskunst.topddit.R
import com.eagskunst.topddit.common.presentation.commonPostModifier
import com.eagskunst.topddit.common.presentation.shimmerEffect
import com.eagskunst.topddit.presentation.post.list.Comment
import com.eagskunst.topddit.presentation.post.list.Content
import com.eagskunst.topddit.presentation.post.list.Post
import com.eagskunst.topddit.presentation.post.list.PostInteractions
import com.eagskunst.topddit.presentation.post.list.PostPlaceholder
import com.eagskunst.topddit.presentation.post.list.Subreddit
import com.eagskunst.topddit.ui.theme.TopdditTheme

@Composable
fun PostDetailViewState(
    viewModel: PostDetailViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.postDetail.observeAsState()
    val lazyListState = rememberLazyListState()
    when (state) {
        is PostDetailViewState.GeneralError -> {
            Text("Error :(") // todo make general error component}
        }

        PostDetailViewState.Loading -> PostDetailPlaceholder()
        is PostDetailViewState.Success -> {
            PostDetail((state as PostDetailViewState.Success).post, lazyListState, modifier)
        }

        null -> Spacer(modifier = Modifier)
    }
}

@Composable
fun PostDetail(
    post: Post,
    lazyListState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
) {
    val comments = post.comments
    LazyColumn(state = lazyListState) {
        item {
            Post(
                post = post,
                showAuthor = true,
                showDivider = false,
                multiContent = true,
                modifier = modifier,
            )
        }
        item {
            Divider(thickness = 10.dp)
        }
        items(count = comments.size, key = { comments[it].id }) {
            Comment(
                comment = comments[it],
                modifier = modifier,
            )
            if (it != comments.lastIndex) {
                Divider(thickness = 10.dp)
            }
        }
        item {
            Divider(thickness = 10.dp)
        }
    }
}

@Composable
fun Comment(
    comment: Comment,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        CommentTitle(authorName = comment.authorName, humanCreatedDate = comment.humanCreatedDate)
        CommentContent(content = comment.content, modifier = Modifier.padding(vertical = 5.dp))
        PostInteractions(upVotes = comment.upVotesRelativeCount)
    }
}

@Composable
fun CommentTitle(
    authorName: String,
    humanCreatedDate: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = "icon_user_$authorName",
            modifier =
                Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(3.dp),
        )
        Text(text = authorName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Text(text = humanCreatedDate, fontSize = 12.sp, fontWeight = FontWeight.Light)
    }
}

@Composable
fun CommentContent(
    content: Content?,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = content?.selfText ?: "", fontSize = 14.sp)
    }
}

@Composable
fun CommentPlaceholder(
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
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
            Spacer(modifier = Modifier.height(5.dp))
            repeat(2) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth(0.95f)
                            .padding(vertical = 2.dp)
                            .height(8.dp)
                            .shimmerEffect(),
                )
            }

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 2.dp)
                        .height(8.dp)
                        .shimmerEffect(),
            )
            Spacer(Modifier.height(8.dp))
        }
        if (showDivider) {
            Divider(modifier = Modifier.shimmerEffect())
        }
    }
}

@Composable
fun PostDetailPlaceholder(commentsCount: Int = 6) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PostPlaceholder(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
        )
        Divider(thickness = 10.dp)
        repeat(commentsCount) {
            CommentPlaceholder(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                showDivider = it < commentsCount - 1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentPreview() {
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
            comments =
                listOf(
                    Comment(
                        id = "2",
                        authorName = "u/Living_Disk_9345",
                        humanCreatedDate = "1 hour ago",
                        upVotesRelativeCount = "265",
                        content =
                            Content(
                                selfText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet velit ac purus vehicula tincidunt. Curabitur ac nibh magna. Duis egestas et nulla a iaculis. Aenean auctor massa pharetra, lacinia magna ut, ultrices justo. Ut sed sapien tincidunt, convallis lacus eu, pretium libero. Quisque vulputate venenatis tristique. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec varius sem eu tortor dapibus, vitae ultricies nisl volutpat. Phasellus ultrices semper sodales. Mauris luctus tellus at libero vestibulum, nec placerat turpis pretium.",
                                imagesUrl = null,
                                videoUrl = null,
                            ),
                    ),
                    Comment(
                        id = "3",
                        authorName = "u/Living_Disk_9345",
                        humanCreatedDate = "1 hour ago",
                        upVotesRelativeCount = "265",
                        content =
                            Content(
                                selfText = "Lorem ipsum dolor sit amet",
                                imagesUrl = null,
                                videoUrl = null,
                            ),
                    ),
                ),
        )
    TopdditTheme {
        PostDetail(post = post, modifier = Modifier.commonPostModifier())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommentPlaceholder() {
    CommentPlaceholder(modifier = Modifier.commonPostModifier())
}
