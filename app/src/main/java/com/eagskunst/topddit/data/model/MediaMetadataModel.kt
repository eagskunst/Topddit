package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaMetadataModel(
    @SerialName("e")
    val e: String,
    @SerialName("id")
    val id: String,
    @SerialName("m")
    val m: String,
    @SerialName("p")
    val p: List<PModel>,
    @SerialName("s")
    val s: SModel,
    @SerialName("status")
    val status: String,
)
