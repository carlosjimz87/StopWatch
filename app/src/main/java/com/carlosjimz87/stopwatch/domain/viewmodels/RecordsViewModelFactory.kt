package com.carlosjimz87.stopwatch.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.stopwatch.utils.ManagedCoroutineScope

class RecordsViewModelFactory (
    private val managedCoroutineScope: ManagedCoroutineScope,
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordsViewModel::class.java)) {
            return RecordsViewModel(
                managedCoroutineScope
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}