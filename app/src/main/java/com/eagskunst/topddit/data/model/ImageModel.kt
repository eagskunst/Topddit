package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageModel(
    @SerialName("id")
    val id: String,
    @SerialName("resolutions")
    val resolutions: List<ResolutionModel>,
    @SerialName("source")
    val source: SourceModel,
    @SerialName("variants")
    val variants: Map<String, String>?,
)
