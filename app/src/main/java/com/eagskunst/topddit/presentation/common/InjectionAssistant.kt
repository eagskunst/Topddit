package com.eagskunst.topddit.presentation.common

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.eagskunst.topddit.di.AppContainer

class InjectionAssistant(
    private val appContainer: AppContainer,
    private val savedStateRegistryOwner: SavedStateRegistryOwner,
) {
    fun getViewModelFactory(): ViewModelProvider.Factory {
        return appContainer.getViewModelFactory(savedStateRegistryOwner)
    }
}
