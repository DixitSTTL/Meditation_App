package com.app.meditation.di

import com.app.MyApp
import com.app.meditation.common.util.CustomMediaPlayer
import com.app.meditation.data.repository.AuthRepositoryImpl
import com.app.meditation.data.repository.NoteRepositoryImpl
import com.app.meditation.data.repository.PlayerRepositoryImpl
import com.app.meditation.data.repository.ServiceRepositoryImpl
import com.app.meditation.data.repository.TuneRepositoryImpl
import com.app.meditation.data.repository.UserRepositoryImpl
import com.app.meditation.database.DatabaseHelper
import com.app.meditation.domain.repository.AuthRepository
import com.app.meditation.domain.repository.NoteRepository
import com.app.meditation.domain.repository.PlayerRepository
import com.app.meditation.domain.repository.ServiceRepository
import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.domain.repository.UserRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun getTuneRepository(firebase: FirebaseFirestore): TuneRepository =
        TuneRepositoryImpl(firebase)


    @Provides
    fun getPlayerRepository(customMediaPlayer: CustomMediaPlayer): PlayerRepository =
        PlayerRepositoryImpl(customMediaPlayer)

    @Provides
    fun getNoteRepository(databaseHelper: DatabaseHelper): NoteRepository =
        NoteRepositoryImpl(databaseHelper)


    @Provides
    fun getUserRepository(databaseHelper: UtilsSharedPreferences, myApp: MyApp): UserRepository =
        UserRepositoryImpl(databaseHelper, myApp)

    @Provides
    fun getAuthRepository(databaseHelper: UtilsSharedPreferences, myApp: MyApp,firebaseAuth: FirebaseAuth,firestore: FirebaseFirestore,firebaseStorage: FirebaseStorage): AuthRepository =
        AuthRepositoryImpl(databaseHelper, myApp,firebaseAuth,firestore,firebaseStorage)

    @Provides
    fun getServiceRepository(myApp: MyApp): ServiceRepository =
        ServiceRepositoryImpl(myApp)


}