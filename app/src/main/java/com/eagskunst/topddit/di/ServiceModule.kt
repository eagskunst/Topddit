package com.eagskunst.topddit.di

import com.eagskunst.topddit.data.service.PostsService

class ServiceModule(networkModule: NetworkModule) {
    val postService = networkModule.retrofitClient.create(PostsService::class.java)
}
