package com.meezzi.fingerchoice

import android.app.Application
import com.kakao.vectormap.KakaoMapSdk
import com.meezzi.fingerchoice.data.source.local.ReviewDatabase

class FingerChoiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        database = ReviewDatabase.getDatabase(this)
        KakaoMapSdk.init(this, BuildConfig.KAKAO_MAP_KEY)
    }

    companion object {
        lateinit var database: ReviewDatabase
    }
}