package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviewModel(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("images")
    val images: List<ImageModel>
)