package com.example.android_2028.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    private val _event = MutableStateFlow<Boolean>(false)
    val event = _event.asStateFlow()
    fun loadSplash(){
        viewModelScope.launch {
            delay(3000)
            _event.emit(true)
        }
    }
}