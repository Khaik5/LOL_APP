package com.example.android_2028.data.repository

import com.example.android_2028.api.RiotApi
import com.example.android_2028.data.model.ChampionDetailUiModel
import com.example.android_2028.data.model.ChampionUiModel
import com.example.android_2028.data.model.ItemUiModel
import com.example.android_2028.data.model.SkillUiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RiotRepository {

    private val version = "14.3.1"

    private val apiService = Retrofit.Builder()
        .baseUrl("https://ddragon.leagueoflegends.com/cdn/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RiotApi::class.java)

    suspend fun getChampions(): List<ChampionUiModel> {
        val response = apiService.getChampions(version)

        if (!response.isSuccessful) return emptyList()

        return response.body()?.data?.values
            ?.map { champion ->
                ChampionUiModel(
                    id = champion.id,
                    name = champion.name,
                    title = champion.title,
                    blurb = champion.blurb,
                    iconUrl = "https://ddragon.leagueoflegends.com/cdn/$version/img/champion/${champion.id}.png",
                    splashUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${champion.id}_0.jpg"
                )
            }
            ?.sortedBy { it.name }
            ?: emptyList()
    }

    suspend fun getItems(): List<ItemUiModel> {
        val response = apiService.getItems(version)

        if (!response.isSuccessful) return emptyList()

        return response.body()?.data?.map { item ->
            ItemUiModel(
                id = item.key,
                name = item.value.name,
                description = item.value.plaintext ?: "",
                iconUrl = "https://ddragon.leagueoflegends.com/cdn/$version/img/item/${item.key}.png"
            )
        } ?: emptyList()
    }

    suspend fun getChampionDetail(championId: String): ChampionDetailUiModel? {
        val response = apiService.getChampionDetail(version, championId)

        if (!response.isSuccessful) return null

        val champion = response.body()?.data?.get(championId) ?: return null

        val passive = SkillUiModel(
            key = "P",
            name = champion.passive.name,
            description = champion.passive.description,
            iconUrl = "https://ddragon.leagueoflegends.com/cdn/$version/img/passive/${champion.passive.image.full}"
        )

        val keys = listOf("Q", "W", "E", "R")

        val spells = champion.spells.mapIndexed { index, spell ->
            SkillUiModel(
                key = keys[index],
                name = spell.name,
                description = spell.description,
                iconUrl = "https://ddragon.leagueoflegends.com/cdn/$version/img/spell/${spell.image.full}"
            )
        }

        return ChampionDetailUiModel(
            id = champion.id,
            name = champion.name,
            title = champion.title,
            lore = champion.lore,
            splashUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${champion.id}_0.jpg",
            role = convertRole(champion.tags.firstOrNull()),
            difficulty = convertDifficulty(champion.info.difficulty),
            skills = listOf(passive) + spells
        )
    }

    private fun convertRole(role: String?): String {
        return when (role) {
            "Fighter" -> "Đấu sĩ"
            "Tank" -> "Đỡ đòn"
            "Mage" -> "Pháp sư"
            "Assassin" -> "Sát thủ"
            "Marksman" -> "Xạ thủ"
            "Support" -> "Hỗ trợ"
            else -> "Không rõ"
        }
    }

    private fun convertDifficulty(value: Int): String {
        return when {
            value <= 3 -> "Dễ"
            value <= 7 -> "Trung bình"
            else -> "Khó"
        }
    }
}