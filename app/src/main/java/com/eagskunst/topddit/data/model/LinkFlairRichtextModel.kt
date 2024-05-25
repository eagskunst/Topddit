package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkFlairRichtextModel(
    @SerialName("a")
    val a: String?,
    @SerialName("e")
    val e: String,
    @SerialName("t")
    val t: String?,
    @SerialName("u")
    val u: String?
)