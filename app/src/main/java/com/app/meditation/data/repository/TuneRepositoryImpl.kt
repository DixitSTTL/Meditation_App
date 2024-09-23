package com.app.meditation.data.repository

import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TuneRepositoryImpl(private var firestore: FirebaseFirestore) : TuneRepository {
    override suspend fun getTunes(): List<DataTunes> {
        var list: List<DataTunes> = emptyList()
        val fb = firestore.collection("tunes").get().await()

        try {
            list = fb.documents.map {
                it.toObject(DataTunes::class.java)!!
            }
        }catch (_:Exception){

        }
        return list
    }
}