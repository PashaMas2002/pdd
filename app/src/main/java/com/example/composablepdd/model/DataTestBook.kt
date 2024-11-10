package com.example.composablepdd.model

data class DataTestBook(
    val title: String,
    val listImageBitmap: List<String> = emptyList(),
    val imageHeight: Int = 120,
    val listLinks: List<Pair<String, String>> = emptyList(),
    val listRedColor: List<String> = emptyList(),
    val listBold: List<String> = emptyList(),
    val description: String
)
