package com.meezzi.fingerchoice

import android.app.Application
import com.meezzi.fingerchoice.data.source.local.ReviewDatabase
import com.naver.maps.map.NaverMapSdk

class FingerChoiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        database = ReviewDatabase.getDatabase(this)
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_MAP_KEY)
    }

    companion object {
        lateinit var database: ReviewDatabase
    }
}