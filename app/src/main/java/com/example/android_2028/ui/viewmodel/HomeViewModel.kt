package com.example.android_2028.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_2028.R
import com.example.android_2028.data.model.BadgeModel
import com.example.android_2028.data.model.ChampionUiModel
import com.example.android_2028.data.repository.RiotRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = RiotRepository()

    private val _champions = MutableStateFlow<List<ChampionUiModel>>(emptyList())
    val champions = _champions.asStateFlow()

    private val _recommendedChampions = MutableStateFlow<List<ChampionUiModel>>(emptyList())
    val recommendedChampions = _recommendedChampions.asStateFlow()

    private val _badge = MutableStateFlow<List<BadgeModel>>(emptyList())
    val badge = _badge.asStateFlow()

    private val _toast = MutableSharedFlow<String>()
    val toast = _toast.asSharedFlow()

    fun loadHomeData() {
        viewModelScope.launch {
            val list = repository.getChampions()

            if (list.isNotEmpty()) {
                _champions.value = list.take(10)

                _recommendedChampions.value = list.filter {
                    it.id == "Vi" ||
                            it.id == "Lux" ||
                            it.id == "Ezreal" ||
                            it.id == "Ahri" ||
                            it.id == "Yasuo"
                }

                _badge.value = listOf(
                    BadgeModel("Demacia", R.drawable.img_demacia),
                    BadgeModel("Noxus", R.drawable.img_noxus),
                    BadgeModel("Shurima", R.drawable.img_shurima)
                )
            } else {
                _toast.emit("Không lấy được dữ liệu")
            }
        }
    }
}