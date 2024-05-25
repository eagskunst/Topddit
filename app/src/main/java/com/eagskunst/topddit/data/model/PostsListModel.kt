package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsListModel(
    @SerialName("data")
    val posts: DataModel,
    @SerialName("kind")
    val kind: String,
)
