package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataModel(
    @SerialName("after")
    val after: String? = null,
    @SerialName("children")
    val children: List<ChildrenModel>,
    @SerialName("dist")
    val dist: Int? = null,
    @SerialName("geo_filter")
    val geoFilter: String,
    @SerialName("modhash")
    val modhash: String,
)
