package com.example.phishguard

import Screenshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class ScreenshotRepository(private val screenshotDao: ScreenshotDao) {

    val allScreenshots: LiveData<List<Screenshot>> = screenshotDao.getScreenshots()
    val searchResults = MutableLiveData<List<Screenshot>>()

    val searchResult = MutableLiveData<Screenshot>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(newscreenshot: Screenshot) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.insertScreenshot(newscreenshot)
        }
    }

    fun deleteScreenshot(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.deleteScreenshot(id)
        }
    }

    fun findScreenshot(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Screenshot>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async screenshotDao.findScreenshots(name)
        }

    fun updateProduct(product: Screenshot) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.updateScreenshot(product)
        }
    }
}