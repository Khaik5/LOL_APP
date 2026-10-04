package com.example.android_2028.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_2028.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    private val userName = "admin"
    private val passWord: String = "123456"
    private val repository: AuthRepository = AuthRepository()
    private val _itShowPassWord = MutableStateFlow<Boolean>(false)
    val itShowPassWord = _itShowPassWord.asStateFlow()
    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()
    private val _itShowLoading = MutableStateFlow<Boolean>(false)
    val itShowLoading = _itShowLoading.asStateFlow()
    fun handleLogin(username: String, password: String){
        viewModelScope.launch {
            _itShowLoading.emit(true)
            delay(1000)
            val result = when {
                username.isBlank() || password.isBlank() -> "The username and password must not be left blank"
                username.length < 5 -> "Username must be at least 5 characters."
                username.length > 28 -> "Username cannot exceed 28 characters."
                password.length < 5 -> "Password must be at least 5 characters."
                password.length > 20 -> "Password cannot exceed 20 characters."
                else ->{
                    val user = repository.createUser(username, password)
                    if(user != null){
                        "Login successful."
                    } else {
                        "Login Fail"
                    }
                }
            }
            _message.emit(result)

            _itShowLoading.emit(false)
        }
    }
    fun showPassWord(){
        _itShowPassWord.value = !_itShowPassWord.value
    }
}