package com.eagskunst.topddit.presentation.common

sealed class Routes(val name: String) {
    data object List : Routes("list")

    data object Detail : Routes("{subredditName}/{postId}") {
        const val SUBREDDIT_ARG_NAME = "subredditName"
        const val POST_ID_ARG_NAME = "postId"

        fun routeLink(
            subredditName: String,
            postId: String,
        ): String {
            return name.replace("{$SUBREDDIT_ARG_NAME}", subredditName.replace("r/", ""))
                .replace("{$POST_ID_ARG_NAME}", postId)
        }
    }
}
