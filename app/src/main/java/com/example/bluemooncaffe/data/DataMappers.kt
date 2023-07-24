package com.example.bluemooncaffe.data

import com.example.bluemooncaffe.database.ProductEntity

fun Product.toProductEntity() = ProductEntity(
    id, categoryId, name, price, servingSize, imageLink, isFavorite
)

fun ProductEntity.ToProduct() = Product(
    id, categoryId, name, price, servingSize, imageLink, isFavorite
)

fun CocktailResponse.ToCocktail() = Cocktail(
    name = strDrink,
    intro = strInstructions,
    image = strDrinkThumb,
    alkoholic = strAlcoholic,
    ingridients = listOf(
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15
    )
)