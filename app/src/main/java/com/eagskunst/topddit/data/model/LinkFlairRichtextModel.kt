package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkFlairRichtextModel(
    @SerialName("a")
    val a: String? = null,
    @SerialName("e")
    val e: String? = null,
    @SerialName("t")
    val t: String? = null,
    @SerialName("u")
    val u: String? = null,
)
