package com.eagskunst.topddit

import android.app.Application
import com.eagskunst.topddit.di.AppContainer
import timber.log.Timber

class TopdditApp : Application() {
    val appContainer by lazy {
        AppContainer(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
