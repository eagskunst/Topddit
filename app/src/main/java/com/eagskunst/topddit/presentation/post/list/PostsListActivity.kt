package com.eagskunst.topddit.presentation.post.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        enableEdgeToEdge()
        setContent {
            TopdditTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TopdditTheme {
        Greeting("Android")
    }
}
