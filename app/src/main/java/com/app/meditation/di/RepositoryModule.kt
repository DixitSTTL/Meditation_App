package com.app.meditation.di

import com.app.MyApp
import com.app.meditation.common.util.CustomMediaPlayer
import com.app.meditation.data.repository.NoteRepository_Impl
import com.app.meditation.data.repository.PlayerRepository_Impl
import com.app.meditation.data.repository.TuneRepository_Impl
import com.app.meditation.data.repository.UserRepository_Impl
import com.app.meditation.database.DatabaseHelper
import com.app.meditation.domain.repository.NoteRepository
import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.domain.repository.UserRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getTuneRepository(firebase: FirebaseFirestore): TuneRepository =
        TuneRepository_Impl(firebase)


    @Provides
    fun getPlayerRepository(customMediaPlayer: CustomMediaPlayer): PlayerRepository =
        PlayerRepository_Impl(customMediaPlayer)

    @Provides
    fun getNoteRepository(databaseHelper: DatabaseHelper): NoteRepository =
        NoteRepository_Impl(databaseHelper)


    @Provides
    fun getUserRepository(databaseHelper: UtilsSharedPreferences,myApp: MyApp): UserRepository =
        UserRepository_Impl(databaseHelper,myApp)


}