package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PModel(
    @SerialName("u")
    val u: String? = null,
    @SerialName("x")
    val x: Int? = null,
    @SerialName("y")
    val y: Int? = null,
)
