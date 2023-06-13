package com.example.phishguard

import Screenshot
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import androidx.lifecycle.viewModelScope
//import com.example.phishguard.network.APIService

import com.example.phishguard.network.data.Screenshot
import kotlinx.coroutines.launch
import java.io.IOException
=======
import android.app.Application
import androidx.lifecycle.MutableLiveData
>>>>>>> 910adcc5968d8d2e9776fc79dc7665218fa0edbd


sealed interface PhishGuardUiState {
    data class Success(val screenshots: List<Screenshot>) : PhishGuardUiState
    data class GetScreenshot(val screenshot: Screenshot) : PhishGuardUiState
    object Error : PhishGuardUiState
    object Loading : PhishGuardUiState
}


class MainViewModel : ViewModel() {
    var phishGuardUiState: PhishGuardUiState by mutableStateOf(PhishGuardUiState.Loading)
        private set

<<<<<<< HEAD
    var screenshot: Screenshot by mutableStateOf(Screenshot(name = ""))
=======
>>>>>>> 910adcc5968d8d2e9776fc79dc7665218fa0edbd
    private var token: String by mutableStateOf("")
    var userId: Int by mutableStateOf(0)

    var screenshotName: String by mutableStateOf("")
    var screenshotDescription: String by mutableStateOf("")

    private var selectedId: Int by mutableStateOf(-1)


    fun sendLogin(username: String, password: String) {
        /*
        viewModelScope.launch {
            phishGuardUiState = try {
                var result = APIService.getInstance().login(
                    User(
                        username = username,
                        password = password
                    )
                )
                token = result.accessToken
                userId = result.id

                val screenshotResult = APIService.getInstance().getScreenshots("Bearer $token")
                PhishGuardUiState.Success(screenshotResult)
            } catch (e: IOException) {
                PhishGuardUiState.Error
            }
        }
        */
    }
    fun newScreenshot(id: String, screenshotName: String, description: String) {
/*
        phishGuardUiState = PhishGuardUiState.Loading
        viewModelScope.launch {
            phishGuardUiState = try {
                APIService.getInstance().insertScreenshot(
                    Screenshot(
                        name = screenshotName)
                )
                val devicesResults = APIService.getInstance().getScreenshots()
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

