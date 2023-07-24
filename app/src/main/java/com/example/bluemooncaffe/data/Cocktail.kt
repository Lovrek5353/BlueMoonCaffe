package com.example.bluemooncaffe.data

data class Cocktail(
    val name: String? = "",
    val intro: String? = "",
    val ingridients: List<String?> = listOf(),
    val image: String? = "",
    val alkoholic: String? = "",
)
