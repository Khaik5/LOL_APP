package com.example.android_2028.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_2028.data.model.ChampionUiModel
import com.example.android_2028.data.model.ItemUiModel
import com.example.android_2028.data.repository.RiotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = RiotRepository()

    private var allChampions = emptyList<ChampionUiModel>()
    private var allItems = emptyList<ItemUiModel>()

    private val _champions = MutableStateFlow<List<ChampionUiModel>>(emptyList())
    val champions = _champions.asStateFlow()

    private val _items = MutableStateFlow<List<ItemUiModel>>(emptyList())
    val items = _items.asStateFlow()

    private val _selectedTab = MutableStateFlow("Champions")
    val selectedTab = _selectedTab.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            allChampions = repository.getChampions()
            allItems = repository.getItems()
        }
    }

    fun changeTab(tab: String) {
        _selectedTab.value = tab
        search("")
    }

    fun search(keyword: String) {
        val query = keyword.trim().lowercase()

        if (query.isBlank()) {
            _champions.value = emptyList()
            _items.value = emptyList()
            return
        }

        when (_selectedTab.value) {
            "Champions" -> {
                _champions.value = allChampions.filter {
                    it.name.lowercase().contains(query) ||
                            it.title.lowercase().contains(query) ||
                            it.id.lowercase().contains(query)
                }
            }

            "Items" -> {
                _items.value = allItems.filter {
                    it.name.lowercase().contains(query) ||
                            it.description.lowercase().contains(query)
                }
            }

            else -> {
                _champions.value = emptyList()
                _items.value = emptyList()
            }
        }
    }
}