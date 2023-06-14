package com.example.phishguard

import Screenshot
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScreenshotDao {
    @Insert
    suspend fun insertScreenshot(screenshot: Screenshot)

    @Query("SELECT * FROM screenshots")
    fun getAllScreenshots(): LiveData<List<Screenshot>>
}