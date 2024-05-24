package com.app.meditation.di

import android.content.Context
import com.app.MyApp
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideUtilSharedPref(@ApplicationContext context: Context): UtilsSharedPreferences =
        UtilsSharedPreferences(context)

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): MyApp =
        context as MyApp

}