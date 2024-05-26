package com.eagskunst.topddit.presentation.post.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eagskunst.topddit.common.presentation.InjectionActivity
import com.eagskunst.topddit.common.presentation.Routes
import com.eagskunst.topddit.di.AppContainer
import com.eagskunst.topddit.ui.theme.TopdditTheme
import timber.log.Timber

class MainActivity : InjectionActivity() {
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
                TopddittNavGraph()
            }
        }
    }

    @Composable
    fun TopddittNavGraph() {
        val navController = rememberNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Timber.i("Navigating to: ${destination.route}")
        }
        NavHost(navController = navController, startDestination = Routes.List.name) {
            composable(Routes.List.name) {
                PostsStates(viewModel = viewModel) {
                    navController.navigate(
                        Routes.Detail.routeLink(
                            it.subreddit?.name ?: "",
                            it.id,
                        ),
                    )
                }
            }
            composable(
                Routes.Detail.name,
                arguments =
                    listOf(
                        navArgument(name = Routes.Detail.SUBREDDIT_ARG_NAME) {
                            type = NavType.StringType
                            nullable = false
                        },
                        navArgument(name = Routes.Detail.POST_ID_ARG_NAME) {
                            type = NavType.StringType
                            nullable = false
                        },
                    ),
            ) { navBackStackEntry ->
                val subredditName = navBackStackEntry.arguments!!.getString("subredditName")!!
                val postId = navBackStackEntry.arguments!!.getString("postId")!!
                Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
                    Text(
                        text = "Pantalla detail - subredditName: $subredditName, postId: $postId",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}
