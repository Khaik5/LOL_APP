package com.example.android_2028.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_2028.data.model.ChampionDetailUiModel
import com.example.android_2028.data.repository.RiotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChampionDetailViewModel : ViewModel() {

    private val repository = RiotRepository()

    private val _detail = MutableStateFlow<ChampionDetailUiModel?>(null)
    val detail = _detail.asStateFlow()

    fun loadChampionDetail(championId: String) {
        viewModelScope.launch {
            _detail.value = repository.getChampionDetail(championId)
        }
    }
}