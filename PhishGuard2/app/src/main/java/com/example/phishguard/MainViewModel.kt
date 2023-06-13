package com.example.phishguard

import Screenshot
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.phishguard.network.makeAPICall


sealed interface PhishGuardUiState {
    data class Success(val screenshots: List<Screenshot>) : PhishGuardUiState
    data class GetScreenshot(val screenshot: Screenshot) : PhishGuardUiState
    object Error : PhishGuardUiState
    object Loading : PhishGuardUiState
}


class MainViewModel : ViewModel() {
    var phishGuardUiState: PhishGuardUiState by mutableStateOf(PhishGuardUiState.Loading)
        private set
    var screenshot: Screenshot by mutableStateOf(Screenshot(name = ""))

    private var token: String by mutableStateOf("")

    var screenshotName: String by mutableStateOf("")
    var screenshotDescription: String by mutableStateOf("")

    private var selectedId: Int by mutableStateOf(-1)


    fun newScreenshot(screenshotName: String) {
        /*
        phishGuardUiState = PhishGuardUiState.Loading
        viewModelScope.launch {
            phishGuardUiState = try {
                makeAPICall().getInstance().insertScreenshot(
                    Screenshot(
                        name = screenshotName)
                )
                PhishGuardUiState.Success(devicesResults)
            } catch (e: IOException) {
                PhishGuardUiState.Error
            }
        }*/
    }
    fun deleteScreenshot(id: String){
        /*
        phishGuardUiState = PhishGuardUiState.Loading
        viewModelScope.launch {
            phishGuardUiState = try {
                APIService.getInstance().deleteScreenshot(
                    id,
                    "Bearer $token"
                )
                val devicesResults = APIService.getInstance().getScreenshots("Bearer $token")
                PhishGuardUiState.Success(devicesResults)
            } catch (e: IOException) {
                PhishGuardUiState.Error
            }
        }
        */
    }
    fun getScreenshot(id: String){
        /*
        phishGuardUiState = PhishGuardUiState.Loading
        viewModelScope.launch {
            phishGuardUiState = try {
                val screenshotResult = APIService.getInstance().getScreenshot(
                    id,
                    "Bearer $token"
                )
                screenshotName = screenshotResult.name
                screenshotDescription = screenshotResult.description

                PhishGuardUiState.GetScreenshot(screenshotResult)
            } catch (e: IOException) {
                PhishGuardUiState.Error
            }
        } */
    }
    fun getScreenshots(){
        /*
        phishGuardUiState = PhishGuardUiState.Loading
        viewModelScope.launch {
            phishGuardUiState = try {
                val screenshotsResults = APIService.getInstance().getScreenshots("Bearer $token")
                PhishGuardUiState.Success(screenshotsResults)
            }catch (e: IOException) {
                PhishGuardUiState.Error
            }
        }
        */
    }
}

