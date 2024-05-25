package com.eagskunst.topddit.presentation.post.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eagskunst.topddit.common.presentation.InjectionActivity
import com.eagskunst.topddit.di.AppContainer
import com.eagskunst.topddit.ui.theme.TopdditTheme

class PostsListActivity : InjectionActivity() {
    private lateinit var viewModel: PostListViewModel

    private val buffer = 1

    override fun inject(
        appContainer: AppContainer,
        savedInstanceState: Bundle?,
    ) {
        val factory = appContainer.postListPresentationModule.createViewModelFactory(this)
        viewModel = factory.create(PostListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTopPosts()
        setContent {
            TopdditTheme {
                PostsStates()
            }
        }
    }

    @Composable
    private fun PostsStates() {
        val posts by viewModel.posts.observeAsState()
        val lazyListState = rememberLazyListState()
        when (posts) {
            is PostViewState.GeneralError -> {
                PostsLists(
                    lazyListState = lazyListState,
                    posts = (posts as? PostViewState.GeneralError)?.posts ?: listOf(),
                    footer = { Text("Error :(") }, // todo make general error component}
                )
            }

            is PostViewState.Loading -> {
                PostsLists(
                    lazyListState = lazyListState,
                    posts = (posts as? PostViewState.Loading)?.posts ?: listOf(),
                    footer = {
                        Column {
                            PostPlaceholder(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp, horizontal = 20.dp),
                            )
                            PostPlaceholder(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp, horizontal = 20.dp),
                            )
                            PostPlaceholder(
                                showDivider = false,
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp, horizontal = 20.dp),
                            )
                        }
                    },
                )
            }

            is PostViewState.Posts ->
                PostsLists(
                    lazyListState = lazyListState,
                    (posts as PostViewState.Posts).posts,
                )

            null -> Spacer(Modifier)
        }
    }

    @Composable
    private fun PostsLists(
        lazyListState: LazyListState,
        posts: List<Post>,
        header: @Composable () -> Unit = {},
        footer: @Composable () -> Unit = {},
    ) {
        val reachedBottom: Boolean by remember {
            derivedStateOf {
                val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
                lastVisibleItem?.index != 0 &&
                    lastVisibleItem?.index == posts.size - buffer
            }
        }
        LaunchedEffect(reachedBottom) {
            viewModel.getTopPosts(reachedBottom = reachedBottom)
        }
        LazyColumn(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
            item { header() }
            items(posts.size, key = { posts[it].id }) { idx ->
                Post(
                    post = posts[idx],
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                )
            }
            item { footer() }
        }
    }
}
