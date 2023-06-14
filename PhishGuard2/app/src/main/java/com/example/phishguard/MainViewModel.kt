package com.example.phishguard

import Screenshot
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

sealed class PhishGuardUiState {
    object Loading : PhishGuardUiState()
    data class Success(val screenshots: LiveData<List<Screenshot>>) : PhishGuardUiState()
    data class Error(val message: String) : PhishGuardUiState()
}

class MainViewModel() : ViewModel() {
    var phishGuardUiState: PhishGuardUiState by mutableStateOf(PhishGuardUiState.Loading)
        private set

    private lateinit var screenshotDao: ScreenshotDao

    init {
        getAllScreenshots()
    }

    fun getAllScreenshots() {
        viewModelScope.launch {
            try {
                val screenshots = screenshotDao.getAllScreenshots()
                phishGuardUiState = PhishGuardUiState.Success(screenshots)
            } catch (e: Exception) {
                phishGuardUiState = PhishGuardUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}