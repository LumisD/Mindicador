package com.lumisdinos.mindicador.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.lumisdinos.mindicador.data.Constants.BASE_URL
import com.lumisdinos.mindicador.data.local.*
import com.lumisdinos.mindicador.data.remote.*
import com.lumisdinos.mindicador.data.repositoryimp.*
import com.lumisdinos.mindicador.domain.repos.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [DataModuleBinds::class])
class DataModule {

    @Singleton
    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10 MB
    }

    @Singleton
    @Provides
    fun provideFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Singleton
    @Provides
    fun provideCurrencyRestApi(context: Context, cache: Cache): CurrencyRestApi {
        val okHttpBuilder = OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .cache(cache)

        return Retrofit.Builder()
            .client(okHttpBuilder.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CurrencyRestApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyDataBase(context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "currency.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(db: CurrencyDatabase) = db.currencyDao()

    @Singleton
    @Provides
    fun provideSerieDataBase(context: Context): SerieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SerieDatabase::class.java,
            "serie.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSerieDao(db: SerieDatabase) = db.serieDao()

    @Singleton
    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("mindicador_pref", Context.MODE_PRIVATE)
    }

}

@Module
abstract class DataModuleBinds {

    //Currency
    @Binds
    abstract fun bindCurrencyRepository(repository: CurrencyDataRepository): CurrencyRepository

    @Binds
    abstract fun bindCurrencyLocal(currencyLocal: CurrencyLocalSourceRepositoryImpl): CurrencyLocalSource

    @Binds
    abstract fun bindCurrencyRemote(currencyRemote: CurrencyRemoteSourceRepositoryImpl): CurrencyRemoteSource

    @Binds
    abstract fun bindCurrencyStateRepository(currencyRemote: CurrencyStateRepositoryImpl): CurrencyStateRepository

    @Binds
    abstract fun bindCurrencyStateRepository(currencyRemote: CurrencyLogicRepositoryImpl): CurrencyLogicRepository

    //Details
    @Binds
    abstract fun bindSerieRepository(repository: SerieDataRepository): SerieRepository

    @Binds
    abstract fun bindCurrencyLocal(currencyLocal: SerieLocalSourceRepositoryImpl): SerieLocalSource

    @Binds
    abstract fun bindSerieRemote(currencyRemote: SerieRemoteSourceRepositoryImpl): SerieRemoteSource

    @Binds
    abstract fun bindSerieStateRepository(currencyRemote: SerieStateRepositoryImpl): SerieStateRepository

    @Binds
    abstract fun bindSerieStateRepository(currencyRemote: SerieLogicRepositoryImpl): SerieLogicRepository

}