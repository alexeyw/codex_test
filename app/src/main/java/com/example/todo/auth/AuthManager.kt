package com.example.todo.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun signUp(email: String, password: String, onResult: (Exception?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onResult(task.exception)
            }
    }

    fun login(email: String, password: String, onResult: (Exception?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onResult(task.exception)
            }
    }

    fun signOut() {
        auth.signOut()
    }

    fun addAuthStateListener(listener: (FirebaseUser?) -> Unit) {
        auth.addAuthStateListener { listener(it.currentUser) }
    }
}
