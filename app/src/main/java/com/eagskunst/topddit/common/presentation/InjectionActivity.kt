package com.eagskunst.topddit.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.eagskunst.topddit.TopdditApp
import com.eagskunst.topddit.di.AppContainer

abstract class InjectionActivity : ComponentActivity() {
    abstract fun inject(
        appContainer: AppContainer,
        savedInstanceState: Bundle?,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as TopdditApp).appContainer, savedInstanceState)
    }
}
