package com.example.phishguard

import Screenshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

// The repository class offers an API to access the room database

class ScreenshotRepository(private val screenshotDao: ScreenshotDao) {

    // the LiveData list of products that is automatically updated in the background
    val allScreenshots: LiveData<List<Screenshot>> = screenshotDao.getScreenshots()
    // the search result that we fill later on
    val searchResults = MutableLiveData<List<Screenshot>>()

    val searchResult = MutableLiveData<Screenshot>()

    // the coroutine scope is needed to run methods asynchronously in the background by the main dispatcher
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    // this method runs the insertProduct method asynchronously in the background
    // so, the call of this method doesn't block the caller (=GUI)
    fun insertProduct(newscreenshot: Screenshot) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.insertScreenshot(newscreenshot)
        }
    }

    // also a asynchronous background call
    fun deleteScreenshot(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.deleteScreenshot(id)
        }
    }

    // this asynchronous background call writes the search result
    // into the MutableLiveDate that we can observe from the GUI
    fun findScreenshot(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    // this is the implementation of the non blocking background call
    // that returns the search result sometime in the future
    private fun asyncFind(name: String): Deferred<List<Screenshot>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async screenshotDao.findScreenshots(name)
        }

    // this method runs the updateProduct method asynchronously in the background
    // so, the call of this method doesn't block the caller (=GUI)
    fun updateProduct(product: Screenshot) {
        coroutineScope.launch(Dispatchers.IO) {
            screenshotDao.updateScreenshot(product)
        }
    }
}