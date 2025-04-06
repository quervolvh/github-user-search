package com.example.myjet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyJet : Application() {

    companion object {}

    lateinit var container: MyJetContainer

    override fun onCreate() {
        super.onCreate()
        container = MyJetContainer(this)
    }

}
