package com.example.android_2028.data.model

data class ChampionDetailResponse(
    val data: Map<String, ChampionDetailDto>
)

data class ChampionDetailDto(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val tags: List<String>,
    val info: ChampionInfoDto,
    val passive: PassiveDto,
    val spells: List<SpellDto>
)

data class ChampionInfoDto(
    val difficulty: Int
)

data class PassiveDto(
    val name: String,
    val description: String,
    val image: ImageDto
)

data class SpellDto(
    val id: String,
    val name: String,
    val description: String,
    val image: ImageDto
)

data class ImageDto(
    val full: String
)