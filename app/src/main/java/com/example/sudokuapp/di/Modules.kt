package com.example.sudokuapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sudokuapp.net.SudokuRestApi
import com.example.sudokuapp.repo.SudokuRepository
import com.example.sudokuapp.utils.SHARED_PREFS_KEY
import com.example.sudokuapp.vm.MenuViewModel
import com.example.sudokuapp.vm.PlaySudokuViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://sugoku.herokuapp.com/"

val viewModelModule = module {
    viewModel { PlaySudokuViewModel(get()) }
    viewModel { MenuViewModel(get()) }
}

val netModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideSudokuRestApi(get()) }
}

val repoModule = module {
    single { provideSharedPreferences(androidContext()) }
    single { SudokuRepository(get(), get()) }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder().build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideSudokuRestApi(retrofit: Retrofit): SudokuRestApi =
    retrofit.create(SudokuRestApi::class.java)

private fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
}