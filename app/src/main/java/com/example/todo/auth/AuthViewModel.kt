package com.example.todo.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _user = MutableStateFlow<FirebaseUser?>(AuthManager.currentUser)
    val user: StateFlow<FirebaseUser?> = _user.asStateFlow()

    init {
        AuthManager.addAuthStateListener { firebaseUser ->
            _user.value = firebaseUser
        }
    }

    fun login(email: String, password: String, onResult: (Exception?) -> Unit) {
        AuthManager.login(email, password) { error ->
            onResult(error)
        }
    }

    fun signUp(email: String, password: String, onResult: (Exception?) -> Unit) {
        AuthManager.signUp(email, password) { error ->
            onResult(error)
        }
    }

    fun logout() {
        viewModelScope.launch { AuthManager.signOut() }
    }
}
