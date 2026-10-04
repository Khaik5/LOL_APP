package com.example.android_2028.data.model

data class ChampionResponse(
    val data: Map<String, ChampionDto>
)

data class ChampionDto(
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String
)