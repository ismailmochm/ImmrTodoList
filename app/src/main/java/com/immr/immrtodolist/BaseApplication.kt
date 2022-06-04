package com.immr.immrtodolist

import android.app.Application
import com.immr.immrtodolist.data.prefs.AppPreferences

class BaseApplication : Application() {

//    var appComponent: AppComponent

    companion object {
        lateinit var instance: BaseApplication
//        lateinit var database: OBDriverDatabase
        lateinit var appPreferences: AppPreferences
    }

    init {
//        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

//        appComponent.inject(this)

//        database = OBDriverDatabase.invoke(this)
        appPreferences = AppPreferences(this)
    }
}