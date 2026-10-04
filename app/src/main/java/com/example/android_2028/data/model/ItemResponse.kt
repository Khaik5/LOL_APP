package com.example.android_2028.data.model

data class ItemResponse(
    val data: Map<String, ItemDto>
)

data class ItemDto(
    val name: String,
    val description: String,
    val plaintext: String?,
    val image: ImageDto
)

