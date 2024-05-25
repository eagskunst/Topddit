package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemModel(
    @SerialName("caption")
    val caption: String,
    @SerialName("id")
    val id: Int,
    @SerialName("media_id")
    val mediaId: String,
    @SerialName("outbound_url")
    val outboundUrl: String
)