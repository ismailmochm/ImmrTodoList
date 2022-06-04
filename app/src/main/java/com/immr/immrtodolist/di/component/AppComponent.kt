package com.immr.immrtodolist.di.component

import com.immr.immrtodolist.di.module.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

//    fun inject(splashActivity: SplashActivity)
}