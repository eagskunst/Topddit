package com.eagskunst.topddit.data.model

import com.eagskunst.topddit.common.JsonAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildrenDataModel(
    @SerialName("allow_live_comments")
    val allowLiveComments: Boolean = false,
    @SerialName("archived")
    val archived: Boolean = false,
    @SerialName("author")
    val author: String? = null,
    @SerialName("author_cakeday")
    val authorCakeday: Boolean = false,
    @SerialName("author_flair_type")
    val authorFlairType: String? = null,
    @SerialName("author_fullname")
    val authorFullname: String? = null,
    @SerialName("author_is_blocked")
    val authorIsBlocked: Boolean = false,
    @SerialName("author_patreon_flair")
    val authorPatreonFlair: Boolean = false,
    @SerialName("author_premium")
    val authorPremium: Boolean = false,
    @SerialName("can_gild")
    val canGild: Boolean = false,
    @SerialName("can_mod_post")
    val canModPost: Boolean = false,
    @SerialName("clicked")
    val clicked: Boolean = false,
    @SerialName("content_categories")
    val contentCategories: List<String>? = null,
    @SerialName("contest_mode")
    val contestMode: Boolean = false,
    @SerialName("created")
    val created: Double? = null,
    @SerialName("created_utc")
    val createdUtc: Double? = null,
    @SerialName("domain")
    val domain: String? = null,
    @SerialName("downs")
    val downs: Int? = null,
    @SerialName("edited")
    @Serializable(with = JsonAsStringSerializer::class)
    val edited: String? = null,
    @SerialName("gallery_data")
    val galleryData: GalleryDataModel? = null,
    @SerialName("gilded")
    val gilded: Int? = null,
    @SerialName("gildings")
    val gildings: Map<String?, String>? = mapOf(),
    @SerialName("hidden")
    val hidden: Boolean = false,
    @SerialName("hide_score")
    val hideScore: Boolean = false,
    @SerialName("id")
    val id: String,
    @SerialName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean = false,
    @SerialName("is_crosspostable")
    val isCrosspostable: Boolean = false,
    @SerialName("is_gallery")
    val isGallery: Boolean = false,
    @SerialName("is_meta")
    val isMeta: Boolean = false,
    @SerialName("is_original_content")
    val isOriginalContent: Boolean = false,
    @SerialName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean = false,
    @SerialName("is_robot_indexable")
    val isRobotIndexable: Boolean = false,
    @SerialName("is_self")
    val isSelf: Boolean = false,
    @SerialName("is_video")
    val isVideo: Boolean = false,
    @SerialName("link_flair_background_color")
    val linkFlairBackgroundColor: String? = null,
    @SerialName("link_flair_css_class")
    val linkFlairCssClass: String? = null,
    @SerialName("link_flair_richtext")
    val linkFlairRichtext: List<LinkFlairRichtextModel>? = null,
    @SerialName("link_flair_template_id")
    val linkFlairTemplateId: String? = null,
    @SerialName("link_flair_text")
    val linkFlairText: String? = null,
    @SerialName("link_flair_text_color")
    val linkFlairTextColor: String? = null,
    @SerialName("link_flair_type")
    val linkFlairType: String? = null,
    @SerialName("locked")
    val locked: Boolean = false,
    @SerialName("media")
    val media: MediaModel? = null,
    @SerialName("media_embed")
    val mediaEmbed: MediaEmbedModel? = null,
    @SerialName("media_only")
    val mediaOnly: Boolean = false,
    @SerialName("name")
    val name: String? = null,
    @SerialName("no_follow")
    val noFollow: Boolean = false,
    @SerialName("num_comments")
    val numComments: Int? = null,
    @SerialName("num_crossposts")
    val numCrossposts: Int? = null,
    @SerialName("over_18")
    val over18: Boolean = false,
    @SerialName("parent_whitelist_status")
    val parentWhitelistStatus: String? = null,
    @SerialName("permalink")
    val permalink: String? = null,
    @SerialName("pinned")
    val pinned: Boolean = false,
    @SerialName("post_hint")
    val postHint: String? = null,
    @SerialName("preview")
    val preview: PreviewModel? = null,
    @SerialName("pwls")
    val pwls: Int? = null,
    @SerialName("quarantine")
    val quarantine: Boolean = false,
    @SerialName("saved")
    val saved: Boolean = false,
    @SerialName("score")
    val score: Int? = null,
    @SerialName("secure_media")
    val secureMedia: SecureMediaModel? = null,
    @SerialName("selftext")
    val selftext: String? = null,
    @SerialName("selftext_html")
    val selftextHtml: String? = null,
    @SerialName("send_replies")
    val sendReplies: Boolean = false,
    @SerialName("spoiler")
    val spoiler: Boolean = false,
    @SerialName("stickied")
    val stickied: Boolean = false,
    @SerialName("subreddit")
    val subreddit: String? = null,
    @SerialName("subreddit_id")
    val subredditId: String? = null,
    @SerialName("subreddit_name_prefixed")
    val subredditNamePrefixed: String? = null,
    @SerialName("subreddit_subscribers")
    val subredditSubscribers: Int? = null,
    @SerialName("subreddit_type")
    val subredditType: String? = null,
    @SerialName("suggested_sort")
    val suggestedSort: String? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("thumbnail_height")
    val thumbnailHeight: Int? = null,
    @SerialName("thumbnail_width")
    val thumbnailWidth: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("total_awards_received")
    val totalAwardsReceived: Int? = null,
    @SerialName("ups")
    val ups: Int? = null,
    @SerialName("upvote_ratio")
    val upvoteRatio: Double? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("url_overridden_by_dest")
    val urlOverriddenByDest: String? = null,
    @SerialName("visited")
    val visited: Boolean = false,
    @SerialName("whitelist_status")
    val whitelistStatus: String? = null,
    @SerialName("wls")
    val wls: Int? = null,
    @SerialName("sr_detail")
    val subredditDetail: SubredditDetailModel? = null,
    @SerialName("body")
    val body: String? = null,
)
