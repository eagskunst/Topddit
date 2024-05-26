package com.eagskunst.topddit.presentation.post.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eagskunst.topddit.presentation.common.ErrorComponent
import com.eagskunst.topddit.presentation.common.commonPostModifier

private const val BUFFER = 1

@Composable
fun PostsStates(
    viewModel: PostListViewModel,
    postPlaceholderCount: Int = 3,
    onPostClick: (Post) -> Unit = {},
) {
    val posts by viewModel.posts.observeAsState()
    val lazyListState = rememberLazyListState()
    when (posts) {
        is PostViewState.GeneralError -> {
            PostsLists(
                onBottomReached = viewModel::getTopPosts,
                onPostClick = onPostClick,
                lazyListState = lazyListState,
                posts = (posts as? PostViewState.GeneralError)?.posts ?: listOf(),
                footer = {
                    ErrorComponent(
                        errorMessage =
                            (posts as PostViewState.GeneralError).error.message
                                ?: "Unknown error",
                    )
                },
            )
        }

        is PostViewState.Loading -> {
            PostsLists(
                onBottomReached = viewModel::getTopPosts,
                onPostClick = onPostClick,
                lazyListState = lazyListState,
                posts = (posts as? PostViewState.Loading)?.posts ?: listOf(),
                footer = {
                    Column {
                        repeat(postPlaceholderCount) {
                            PostPlaceholder(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp, horizontal = 20.dp),
                                showDivider = it != postPlaceholderCount - 1,
                            )
                        }
                    }
                },
            )
        }

        is PostViewState.Posts ->
            PostsLists(
                onBottomReached = viewModel::getTopPosts,
                onPostClick = onPostClick,
                lazyListState = lazyListState,
                posts = (posts as PostViewState.Posts).posts,
            )

        null -> Spacer(Modifier)
    }
}

@Composable
fun PostsLists(
    lazyListState: LazyListState,
    posts: List<Post>,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onBottomReached: (Boolean) -> Unit = {},
    onPostClick: (Post) -> Unit = {},
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
) {
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 &&
                lastVisibleItem?.index == posts.size - BUFFER
        }
    }
    LaunchedEffect(reachedBottom) {
        onBottomReached(reachedBottom)
    }
    LazyColumn(modifier = modifier, state = lazyListState) {
        item { header() }
        items(posts.size, key = { posts[it].id }) { idx ->
            Post(
                post = posts[idx],
                modifier = Modifier.commonPostModifier(),
                onPostClick = onPostClick,
            )
        }
        item { footer() }
    }
}
