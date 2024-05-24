package com.app.meditation.data.repository

import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TuneRepository_Impl(var firestore: FirebaseFirestore) : TuneRepository {
    override suspend fun getTunes(): MutableList<DataTunes> {
        val list: MutableList<DataTunes> = mutableListOf()
        val fb = firestore.collection("tunes").get().await()

        fb?.documents?.forEach { doc ->
            val dataTunes = doc.toObject(DataTunes::class.java)
            dataTunes?.let {
                list.add(dataTunes)
            }

        }

        return list
    }
}