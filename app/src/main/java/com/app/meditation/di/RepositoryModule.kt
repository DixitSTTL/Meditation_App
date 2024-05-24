package com.app.meditation.di

import com.app.meditation.data.repository.TuneRepository_Impl
import com.app.meditation.domain.repository.TuneRepository
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


}