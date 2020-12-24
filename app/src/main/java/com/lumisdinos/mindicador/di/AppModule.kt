package com.lumisdinos.mindicador.di

import android.content.Context
import android.net.ConnectivityManager
import com.lumisdinos.mindicador.common.util.NetworkStateManager
import dagger.Module
import dagger.Provides


@Module(includes = [
    ViewModelModule::class
])
class AppModule {

    @Provides
    fun provideNetworkStateManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

}

