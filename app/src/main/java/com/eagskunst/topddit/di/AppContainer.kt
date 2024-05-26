package com.eagskunst.topddit.di

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner

class AppContainer(context: Context) {
    private val threadModule = ThreadModule()
    private val mapperModule = MapperModule()
    private val networkModule = NetworkModule(context)
    private val serviceModule = ServiceModule(networkModule)
    private val postsDataModule = PostsDataModule(serviceModule, mapperModule, threadModule)
    private var viewModelFactory: TopdditViewModelFactory? = null

    fun getViewModelFactory(savedStateRegistryOwner: SavedStateRegistryOwner): TopdditViewModelFactory {
        return viewModelFactory ?: TopdditViewModelFactory(
            postsDataModule,
            mapperModule,
            savedStateRegistryOwner,
        ).also {
            viewModelFactory = it
        }
    }
}
