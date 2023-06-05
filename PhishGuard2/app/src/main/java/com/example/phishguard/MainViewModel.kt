package com.example.phishguard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isDrawerOpen = MutableLiveData(false)
    val isDrawerOpen: LiveData<Boolean>
        get() = _isDrawerOpen

    fun openDrawer() {
        _isDrawerOpen.value = true
    }

    fun closeDrawer() {
        _isDrawerOpen.value = false
    }
}

