package com.example.bluemooncaffe.data

import com.example.bluemooncaffe.database.ProductEntity

fun Product.toProductEntity()=ProductEntity(
    id,
    categoryId,
    name,
    price,
    servingSize,
    imageLink,
    isFavorite
)

fun ProductEntity.ToProduct()=Product(
    id,
    categoryId,
    name,
    price,
    servingSize,
    imageLink,
    isFavorite
)