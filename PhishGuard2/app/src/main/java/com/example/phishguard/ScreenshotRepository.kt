package com.example.phishguard

import Screenshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class ScreenshotRepository(private val screenshotDao: ScreenshotDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertScreenshot(screenshot: Screenshot) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.insertScreenshot(screenshot)
        }
    }


    fun getAllScreenshots() {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.getAllScreenshots()
        }
    }

}