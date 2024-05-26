package com.eagskunst.topddit.di

import android.content.Context

class AppContainer(context: Context) {
    private val threadModule = ThreadModule()
    private val mapperModule = MapperModule()
    private val networkModule = NetworkModule(context)
    private val serviceModule = ServiceModule(networkModule)
    val postsDataModule = PostsDataModule(serviceModule, mapperModule, threadModule)
    val postListPresentationModule = PostsListPresentationModule(postsDataModule, mapperModule)
}
