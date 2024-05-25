package com.eagskunst.topddit.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RedditVideoModel(
    @SerialName("bitrate_kbps")
    val bitrateKbps: Int,
    @SerialName("dash_url")
    val dashUrl: String,
    @SerialName("duration")
    val duration: Int,
    @SerialName("fallback_url")
    val fallbackUrl: String,
    @SerialName("has_audio")
    val hasAudio: Boolean,
    @SerialName("height")
    val height: Int,
    @SerialName("hls_url")
    val hlsUrl: String,
    @SerialName("is_gif")
    val isGif: Boolean,
    @SerialName("scrubber_media_url")
    val scrubberMediaUrl: String,
    @SerialName("transcoding_status")
    val transcodingStatus: String,
    @SerialName("width")
    val width: Int
)