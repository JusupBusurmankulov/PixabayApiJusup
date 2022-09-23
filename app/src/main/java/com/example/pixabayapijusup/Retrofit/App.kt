package com.example.pixabayapijusup.Retrofit

import android.app.Application

class App: Application() {
companion object{
    lateinit var api: PixaApi
}

    override fun onCreate() {
        super.onCreate()
        api = RetrofitService().api
    }
}