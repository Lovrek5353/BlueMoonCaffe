package com.example.bluemooncaffe.network

import com.example.bluemooncaffe.data.InitialResponse
import com.example.bluemooncaffe.network.HTTPRoutes.randomCocktail
import io.ktor.client.*
import io.ktor.client.request.*

interface CocktailAPI {
    suspend fun fetchRandomCocktail(): InitialResponse
}

internal class CocktailAPIImpl(private val client: HttpClient) : CocktailAPI {
    override suspend fun fetchRandomCocktail(): InitialResponse =
        client.get(
            "${randomCocktail}"
        )
}