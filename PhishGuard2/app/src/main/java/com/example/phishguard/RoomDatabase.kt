package com.example.phishguard

import Screenshot
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Screenshot::class)], version = 1)
abstract class RoomDatabase: RoomDatabase() {

    abstract fun screenshotDao(): ScreenshotDao

    companion object {

        private var INSTANCE: RoomDatabase? = null

        fun getInstance(context: Context): RoomDatabase {
            // the synchronized block cannot be interrupted by another thread
            // (=atomic, see transactions)
            synchronized(this) {
                var instance = INSTANCE
                // create one instance and reuse it
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabase::class.java,
                        "screenshot_database" // the name of the sqlite file
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}