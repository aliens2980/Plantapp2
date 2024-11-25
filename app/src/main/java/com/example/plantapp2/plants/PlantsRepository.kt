package com.example.plantapp2.plants

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
//import com.example.plantapp2.data.FirebaseCallback
import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.Response
import com.example.plantapp2.utils.Constants.PLANTS_REF


class PlantsRepository(
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val plantRef: CollectionReference = rootRef.collection(PLANTS_REF)
) {
    /*
    fun getResponseFromFirestoreUsingCallback(callback: FirebaseCallback) {
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
            callback.onResponse(response)
        }
    } */

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

    /*

    suspend fun getResponseFromFirestoreUsingCoroutines(): Response {
        val response = Response()
        try {
            response.plants = plantRef.get().await().documents.mapNotNull { snapShot ->
                snapShot.toObject(Plant::class.java)
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }

    fun getResponseFromFirestoreUsingFlow() = flow {
        val response = Response()
        try {
            response.plants = plantRef.get().await().documents.mapNotNull { snapShot ->
                snapShot.toObject(Plant::class.java)
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        emit(response)
    }
     */
}