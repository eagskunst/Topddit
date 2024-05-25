package com.eagskunst.topddit.data.service

import com.eagskunst.topddit.data.model.PostsListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {
    @GET("/top.json")
    suspend fun getTopPosts(
        @Query(value = "after", encoded = true) lastPostId: String?,
        @Query(value = "limit", encoded = true) limit: Int = 10,
        @Query(value = "sr_detail", encoded = true) type: Int = 1,
        @Query(value = "raw_json", encoded = true) rawJson: Int = 1,
    ): PostsListModel
}
