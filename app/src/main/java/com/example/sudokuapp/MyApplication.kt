package com.example.sudokuapp

import android.app.Application
import com.example.sudokuapp.di.netModule
import com.example.sudokuapp.di.repoModule
import com.example.sudokuapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(viewModelModule, netModule, repoModule)

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(moduleList)
        }
    }
}