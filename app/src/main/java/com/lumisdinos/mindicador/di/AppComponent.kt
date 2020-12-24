package com.lumisdinos.mindicador.di

import android.app.Application
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
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MindicadorApp)
}