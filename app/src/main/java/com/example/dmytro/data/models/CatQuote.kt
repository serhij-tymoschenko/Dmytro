package com.example.dmytro.data.models

data class CatQuote(
    val quote: String
)

val catQuotes = MutableList(100) { CatQuote("meow") }