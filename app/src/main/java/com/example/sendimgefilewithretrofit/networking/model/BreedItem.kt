package com.example.sendimgefilewithretrofit.networking.model

data class BreedItem(
    val breeds: List<Any>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)