package com.example.phishguard

import Screenshot
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// This DAO interface specifies all methods that a class must implement
// to access the products table in the SQLite DB
// We don't have to implement the methods ourself because
// the class with the complete implementation is generated automatically
// We just have to specify the SQL statements/operations
@Dao
interface ScreenshotDao {

    @Insert
    fun insertScreenshot(screenshot: Screenshot)

    @Query("SELECT * FROM screenshots WHERE screenshotName = :name")
    fun findScreenshots(name: String): List<Screenshot>

    @Query("DELETE FROM screenshots WHERE id = :id")
    fun deleteScreenshot(id: Int)

    // note: the getAllProducts method returns a LiveData object
    // that is automatically updated asynchronous in the background
    @Query("SELECT * FROM screenshots")
    fun getScreenshots(): LiveData<List<Screenshot>>

    @Update
    fun updateScreenshot(product: Screenshot)
}