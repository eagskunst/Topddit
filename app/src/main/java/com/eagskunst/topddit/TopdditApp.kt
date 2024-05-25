package com.eagskunst.topddit

import android.app.Application
import timber.log.Timber

class TopdditApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
