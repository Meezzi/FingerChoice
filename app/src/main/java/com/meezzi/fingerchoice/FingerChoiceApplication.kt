package com.meezzi.fingerchoice

import android.app.Application
import com.meezzi.fingerchoice.data.source.local.ReviewDatabase

class FingerChoiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        database = ReviewDatabase.getDatabase(this)
    }

    companion object {
        lateinit var database: ReviewDatabase
    }
}