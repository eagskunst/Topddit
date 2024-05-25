package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SModel(
    @SerialName("u")
    val u: String,
    @SerialName("x")
    val x: Int,
    @SerialName("y")
    val y: Int
)