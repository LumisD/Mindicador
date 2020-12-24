package com.lumisdinos.mindicador.di

import com.lumisdinos.mindicador.ui.fragment.HomeListFragment
import com.lumisdinos.mindicador.ui.fragment.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeListFragment(): HomeListFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

}