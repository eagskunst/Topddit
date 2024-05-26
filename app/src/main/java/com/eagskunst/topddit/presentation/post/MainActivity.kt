package com.eagskunst.topddit.presentation.post

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eagskunst.topddit.TopdditApp
import com.eagskunst.topddit.di.AppContainer
import com.eagskunst.topddit.presentation.common.InjectionAssistant
import com.eagskunst.topddit.presentation.common.Routes
import com.eagskunst.topddit.presentation.common.commonPostModifier
import com.eagskunst.topddit.presentation.post.detail.PostDetailViewModel
import com.eagskunst.topddit.presentation.post.detail.PostDetailViewState
import com.eagskunst.topddit.presentation.post.list.PostListViewModel
import com.eagskunst.topddit.presentation.post.list.PostsStates
import com.eagskunst.topddit.ui.theme.TopdditTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var injectionAssistant: InjectionAssistant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContent {
            TopdditTheme {
                TopdditNavGraph()
            }
        }
    }

    private fun inject() {
        val appContainer: AppContainer = (application as TopdditApp).appContainer
        injectionAssistant = InjectionAssistant(appContainer, this)
        val viewModelFactory = injectionAssistant.getViewModelFactory()
        viewModelProvider = ViewModelProvider(this, viewModelFactory)
    }

    private fun <T : ViewModel> getViewModel(viewModelClass: Class<T>): T {
        return viewModelProvider[viewModelClass]
    }

    @Composable
    fun TopdditNavGraph() {
        val navController = rememberNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Timber.i("Navigating to: ${destination.route}")
        }
        NavHost(navController = navController, startDestination = Routes.List.name) {
            composable(Routes.List.name) {
                val viewModel = getViewModel(PostListViewModel::class.java)
                viewModel.getTopPosts()
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
                val viewModel = getViewModel(PostDetailViewModel::class.java)
                PostDetailViewState(
                    viewModel = viewModel,
                    modifier = Modifier.commonPostModifier(),
                ) {
                    navController.popBackStack()
                }
                viewModel.requestPostDetail(subredditName, postId)
            }
        }
    }
}
