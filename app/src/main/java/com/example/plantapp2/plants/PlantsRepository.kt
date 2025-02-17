package com.example.plantapp2.plants

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.Response
import com.example.plantapp2.utils.Constants.PLANTS_REF
import kotlinx.coroutines.tasks.await


class PlantsRepository(
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val plantRef: CollectionReference = rootRef.collection(PLANTS_REF)
) {

    fun getResponseFromFirestoreUsingLiveData() : MutableLiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        plantRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.plants = result.documents.mapNotNull { snapShot ->
                        snapShot.toObject(Plant::class.java)
                    }
                }
            } else {
                response.exception = task.exception
            }
            mutableLiveData.value = response
        }
        return mutableLiveData
    }
    suspend fun getPlantByName(name: String): Plant? {
        return try {
            val snapshot = plantRef.whereEqualTo("name", name).get().await()

            val documents = snapshot.documents
            if (documents.isNotEmpty()) {
                documents.first().toObject(Plant::class.java)
            } else {
                null // If no plant is found
            }
        } catch (e: Exception) {
            null
        }
    }
}