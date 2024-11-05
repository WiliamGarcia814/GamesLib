package com.whgarcia.gameslib

import android.app.Application
import com.whgarcia.gameslib.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GamesLibApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GamesLibApplication)
            androidLogger()

            modules(appModule)
        }
    }
}