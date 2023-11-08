package com.example.smartfertigation.data

import android.annotation.SuppressLint
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.model.nutrients
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@UnstableApi class NutrientsRepository {

    private var db = Firebase.firestore

    @SuppressLint("Range")
    suspend fun createNutrients(nutrients: nutrients) :  ResourceRemote<String?> {
        return try {
            val document = db.collection("users_nutrients").document()
            nutrients.id = document.id
            db.collection("users_nutrients").document(document.id).set(nutrients).await()
            ResourceRemote.Success(data = document.id)
        } catch (e: FirebaseFirestoreException){
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseException){
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    @SuppressLint("Range")
    suspend fun loadNutrients() : ResourceRemote<QuerySnapshot?> {
        return try {
            val result = db.collection("users_nutrients").get().await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseException){
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    @SuppressLint("Range")
    suspend fun deletenutrients(nutrients: nutrients?): ResourceRemote<String?> {
        return try {
            val result = nutrients?.id?.let { db.collection("users_nutrients").document(it).delete().await() }
            ResourceRemote.Success(data = nutrients?.id)
        } catch (e: FirebaseFirestoreException){
            Log.e("FirebaseFirestoreException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseException){
            Log.e("FirebaseException", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

}