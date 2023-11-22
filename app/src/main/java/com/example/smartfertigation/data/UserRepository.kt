package com.example.smartfertigation.data

import android.annotation.SuppressLint
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@UnstableApi class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth

    private var db = Firebase.firestore
    suspend fun registerUser(email: String, password: String) : ResourceRemote<String?>{
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            //result.user?.sendEmailVerification()
            //auth.sendPasswordResetEmail(email)
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException){
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    @SuppressLint("Range")
    suspend fun loginUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            ResourceRemote.Success(data = result.user?.uid)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseAuthException", e.localizedMessage)
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
    suspend fun createUser(user: User):  ResourceRemote<String?> {
        return try {
            user.uid?.let { db.collection("users").document(it).set(user).await() }
            ResourceRemote.Success(data = user.uid)
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
    suspend fun verifyUser(): ResourceRemote<Boolean> {
        return try{
            val userLoaded = auth.currentUser != null
            ResourceRemote.Success(data = userLoaded)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseAuthException", e.localizedMessage)
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
    fun signOut(): ResourceRemote<Boolean> {
        return try{
            auth.signOut()
            ResourceRemote.Success(data = true)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseAuthException", e.localizedMessage)
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



