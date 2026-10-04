package com.example.android_2028.data.model

data class ChampionDetailUiModel(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val splashUrl: String,
    val role: String,
    val difficulty: String,
    val skills: List<SkillUiModel>
)

data class SkillUiModel(
    val key: String,
    val name: String,
    val description: String,
    val iconUrl: String
)