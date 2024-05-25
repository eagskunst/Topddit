package com.eagskunst.topddit.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildrenDataModel(
    @SerialName("allow_live_comments")
    val allowLiveComments: Boolean,
    @SerialName("archived")
    val archived: Boolean,
    @SerialName("author")
    val author: String,
    @SerialName("author_cakeday")
    val authorCakeday: Boolean = false,
    @SerialName("author_flair_type")
    val authorFlairType: String,
    @SerialName("author_fullname")
    val authorFullname: String,
    @SerialName("author_is_blocked")
    val authorIsBlocked: Boolean,
    @SerialName("author_patreon_flair")
    val authorPatreonFlair: Boolean,
    @SerialName("author_premium")
    val authorPremium: Boolean,
    @SerialName("can_gild")
    val canGild: Boolean,
    @SerialName("can_mod_post")
    val canModPost: Boolean,
    @SerialName("clicked")
    val clicked: Boolean,
    @SerialName("content_categories")
    val contentCategories: List<String>?,
    @SerialName("contest_mode")
    val contestMode: Boolean,
    @SerialName("created")
    val created: Double,
    @SerialName("created_utc")
    val createdUtc: Double,
    @SerialName("domain")
    val domain: String,
    @SerialName("downs")
    val downs: Int,
    @SerialName("edited")
    val edited: Boolean,
    @SerialName("gallery_data")
    val galleryData: GalleryDataModel? = null,
    @SerialName("gilded")
    val gilded: Int,
    @SerialName("gildings")
    val gildings: Map<String, String>?,
    @SerialName("hidden")
    val hidden: Boolean,
    @SerialName("hide_score")
    val hideScore: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean,
    @SerialName("is_crosspostable")
    val isCrosspostable: Boolean,
    @SerialName("is_gallery")
    val isGallery: Boolean = false,
    @SerialName("is_meta")
    val isMeta: Boolean,
    @SerialName("is_original_content")
    val isOriginalContent: Boolean,
    @SerialName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,
    @SerialName("is_robot_indexable")
    val isRobotIndexable: Boolean,
    @SerialName("is_self")
    val isSelf: Boolean,
    @SerialName("is_video")
    val isVideo: Boolean,
    @SerialName("link_flair_background_color")
    val linkFlairBackgroundColor: String? = null,
    @SerialName("link_flair_css_class")
    val linkFlairCssClass: String?,
    @SerialName("link_flair_richtext")
    val linkFlairRichtext: List<LinkFlairRichtextModel>,
    @SerialName("link_flair_template_id")
    val linkFlairTemplateId: String? = null,
    @SerialName("link_flair_text")
    val linkFlairText: String?,
    @SerialName("link_flair_text_color")
    val linkFlairTextColor: String?,
    @SerialName("link_flair_type")
    val linkFlairType: String,
    @SerialName("locked")
    val locked: Boolean,
    @SerialName("media")
    val media: MediaModel?,
    @SerialName("media_embed")
    val mediaEmbed: MediaEmbedModel?,
    @SerialName("media_metadata")
    val mediaMetadata: Map<String, MediaMetadataModel>? = null,
    @SerialName("media_only")
    val mediaOnly: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("no_follow")
    val noFollow: Boolean,
    @SerialName("num_comments")
    val numComments: Int,
    @SerialName("num_crossposts")
    val numCrossposts: Int,
    @SerialName("over_18")
    val over18: Boolean,
    @SerialName("parent_whitelist_status")
    val parentWhitelistStatus: String?,
    @SerialName("permalink")
    val permalink: String,
    @SerialName("pinned")
    val pinned: Boolean,
    @SerialName("post_hint")
    val postHint: String? = null,
    @SerialName("preview")
    val preview: PreviewModel? = null,
    @SerialName("pwls")
    val pwls: Int?,
    @SerialName("quarantine")
    val quarantine: Boolean,
    @SerialName("saved")
    val saved: Boolean,
    @SerialName("score")
    val score: Int,
    @SerialName("secure_media")
    val secureMedia: SecureMediaModel?,
    @SerialName("selftext")
    val selftext: String,
    @SerialName("selftext_html")
    val selftextHtml: String?,
    @SerialName("send_replies")
    val sendReplies: Boolean,
    @SerialName("spoiler")
    val spoiler: Boolean,
    @SerialName("stickied")
    val stickied: Boolean,
    @SerialName("subreddit")
    val subreddit: String,
    @SerialName("subreddit_id")
    val subredditId: String,
    @SerialName("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    @SerialName("subreddit_subscribers")
    val subredditSubscribers: Int,
    @SerialName("subreddit_type")
    val subredditType: String,
    @SerialName("suggested_sort")
    val suggestedSort: String?,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Int,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Int,
    @SerialName("title")
    val title: String,
    @SerialName("total_awards_received")
    val totalAwardsReceived: Int,
    @SerialName("ups")
    val ups: Int,
    @SerialName("upvote_ratio")
    val upvoteRatio: Double,
    @SerialName("url")
    val url: String,
    @SerialName("url_overridden_by_dest")
    val urlOverriddenByDest: String? = null,
    @SerialName("visited")
    val visited: Boolean,
    @SerialName("whitelist_status")
    val whitelistStatus: String?,
    @SerialName("wls")
    val wls: Int?,
)
