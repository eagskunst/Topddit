package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaMetadataModel(
    @SerialName("e")
    val e: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("m")
    val m: String? = null,
    @SerialName("p")
    val p: List<PModel>? = null,
    @SerialName("s")
    val s: SModel? = null,
    @SerialName("status")
    val status: String? = null,
)
