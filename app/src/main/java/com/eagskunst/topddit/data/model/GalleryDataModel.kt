package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GalleryDataModel(
    @SerialName("items")
    val items: List<ItemModel>
)