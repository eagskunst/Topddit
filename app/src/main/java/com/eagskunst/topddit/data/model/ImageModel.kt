package com.eagskunst.topddit.data.model

import com.eagskunst.topddit.common.JsonAsStringSerializer
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
    @Serializable(with = JsonAsStringSerializer::class)
    val variants: String?,
)
