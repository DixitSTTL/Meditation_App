package com.app.meditation.di

import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Singleton
    @Provides
    fun provideFireStoreInstance(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideFirebaseStorageInstance(): FirebaseStorage = Firebase.storage
}