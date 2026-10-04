package com.example.android_2028.api

import com.example.android_2028.data.model.ChampionDetailResponse
import com.example.android_2028.data.model.ChampionResponse
import com.example.android_2028.data.model.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApi {

    @GET("{version}/data/vi_VN/champion.json")
    suspend fun getChampions(
        @Path("version") version: String
    ): Response<ChampionResponse>

    @GET("{version}/data/vi_VN/champion/{championId}.json")
    suspend fun getChampionDetail(
        @Path("version") version: String,
        @Path("championId") championId: String
    ): Response<ChampionDetailResponse>

    @GET("{version}/data/vi_VN/item.json")
    suspend fun getItems(
        @Path("version") version: String
    ): Response<ItemResponse>
}