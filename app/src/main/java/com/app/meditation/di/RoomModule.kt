package com.app.meditation.di

import androidx.room.Room
import com.app.MyApp
import com.app.meditation.database.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomInstance(app: MyApp) =
        Room.databaseBuilder(app, DatabaseHelper::class.java, "meditationDb").build()
}