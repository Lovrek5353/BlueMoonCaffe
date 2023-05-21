package com.example.bluemooncaffe.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailResponse(
    @SerialName("strDrink")
    val strDrink: String?,
    @SerialName("strInstructions")
    val strInstructions: String?,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String?,
    @SerialName("strIngredient1")
    val strIngredient1: String?,
    @SerialName("strIngredient2")
    val strIngredient2: String?,
    @SerialName("strIngredient3")
    val strIngredient3: String?,
    @SerialName("strIngredient4")
    val strIngredient4: String?,
    @SerialName("strIngredient5")
    val strIngredient5: String?,
    @SerialName("strIngredient6")
    val strIngredient6: String? = null,
    @SerialName("strIngredient7")
    val strIngredient7: String? = null,
    @SerialName("strIngredient8")
    val strIngredient8: String? = null,
    @SerialName("strIngredient9")
    val strIngredient9: String? = null,
    @SerialName("strIngredient10")
    val strIngredient10: String? = null,
    @SerialName("strIngredient11")
    val strIngredient11: String? = null,
    @SerialName("strIngredient12")
    val strIngredient12: String? = null,
    @SerialName("strIngredient13")
    val strIngredient13: String? = null,
    @SerialName("strIngredient14")
    val strIngredient14: String? = null,
    @SerialName("strIngredient15")
    val strIngredient15: String? = null,
    @SerialName("strAlcoholic")
    val strAlcoholic: String,
)

@Serializable
data class InitialResponse(
    @SerialName("drinks")
    val initialObject: ArrayList<CocktailResponse> = arrayListOf()
)