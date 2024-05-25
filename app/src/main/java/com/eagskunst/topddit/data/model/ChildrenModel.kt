package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildrenModel(
    @SerialName("data")
    val info: ChildrenDataModel,
    @SerialName("kind")
    val kind: String,
)
