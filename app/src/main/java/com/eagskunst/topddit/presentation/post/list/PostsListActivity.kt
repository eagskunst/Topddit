package com.eagskunst.topddit.presentation.post.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eagskunst.topddit.common.presentation.InjectionActivity
import com.eagskunst.topddit.di.AppContainer
import com.eagskunst.topddit.ui.theme.TopdditTheme

class PostsListActivity : InjectionActivity() {
    private lateinit var viewModel: PostListViewModel

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
        when (posts) {
            is PostViewState.GeneralError -> {
                PostsLists(posts = (posts as? PostViewState.GeneralError)?.posts ?: listOf())
                Text("Error :(") // todo make general error component
            }

            is PostViewState.Loading -> {
                PostsLists(posts = (posts as? PostViewState.Loading)?.posts ?: listOf())
                CircularProgressIndicator(
                    modifier = Modifier.padding(24.dp),
                    color = Color.Red,
                    strokeWidth = 10.dp,
                )
            }

            is PostViewState.Posts -> PostsLists((posts as PostViewState.Posts).posts)
            null -> Spacer(Modifier)
        }
    }

    @Composable
    private fun PostsLists(posts: List<Post>) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(posts.size, key = { posts[it].id }) { idx ->
                Post(post = posts[idx])
            }
        }
    }
}
