package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubredditDetailModel(
    @SerialName("accept_followers")
    val acceptFollowers: Boolean = false,
    @SerialName("allowed_media_in_comments")
    val allowedMediaInComments: List<String>,
    @SerialName("banner_img")
    val bannerImg: String?,
    @SerialName("banner_size")
    val bannerSize: List<Int> = listOf(),
    @SerialName("community_icon")
    val communityIcon: String?,
    @SerialName("created")
    val created: Int?,
    @SerialName("created_utc")
    val createdUtc: Int?,
    @SerialName("default_set")
    val defaultSet: Boolean = false,
    @SerialName("description")
    val description: String?,
    @SerialName("disable_contributor_requests")
    val disableContributorRequests: Boolean = false,
    @SerialName("display_name")
    val displayName: String?,
    @SerialName("display_name_prefixed")
    val displayNamePrefixed: String?,
    @SerialName("free_form_reports")
    val freeFormReports: Boolean = false,
    @SerialName("header_img")
    val headerImg: String?,
    @SerialName("header_size")
    val headerSize: List<Int?>,
    @SerialName("icon_color")
    val iconColor: String?,
    @SerialName("icon_img")
    val iconImg: String?,
    @SerialName("icon_size")
    val iconSize: List<Int?>,
    @SerialName("key_color")
    val keyColor: String?,
    @SerialName("link_flair_enabled")
    val linkFlairEnabled: Boolean = false,
    @SerialName("link_flair_position")
    val linkFlairPosition: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("over_18")
    val over18: Boolean = false,
    @SerialName("primary_color")
    val primaryColor: String?,
    @SerialName("public_description")
    val publicDescription: String?,
    @SerialName("quarantine")
    val quarantine: Boolean = false,
    @SerialName("restrict_commenting")
    val restrictCommenting: Boolean = false,
    @SerialName("restrict_posting")
    val restrictPosting: Boolean = false,
    @SerialName("show_media")
    val showMedia: Boolean = false,
    @SerialName("submit_link_label")
    val submitLinkLabel: String?,
    @SerialName("submit_text_label")
    val submitTextLabel: String?,
    @SerialName("subreddit_type")
    val subredditType: String?,
    @SerialName("subscribers")
    val subscribers: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
)
