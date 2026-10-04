package com.example.android_2028.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait

class AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth
    suspend fun createUser(email: String, password: String): FirebaseUser?{
        val result = auth.createUserWithEmailAndPassword(email, password)
            .await()
        return result.user
    }
    suspend fun loginHandle(email: String, password: String): FirebaseUser?{
        val result = auth.signInWithEmailAndPassword(email, password)
            .await()
        return result.user
    }
}