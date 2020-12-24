package com.lumisdinos.mindicador.di

import android.content.Context
import com.lumisdinos.mindicador.MindicadorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivitiesBuildersModule::class,
        DataModule::class,
        ContextModule::class
    ]
)
interface AppComponent : AndroidInjector<MindicadorApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}