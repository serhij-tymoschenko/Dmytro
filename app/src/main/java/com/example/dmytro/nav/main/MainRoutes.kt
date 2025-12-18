package com.example.dmytro.nav.main

import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoutes {
    @Serializable
    data object None : MainRoutes()

    @Serializable
    data object Top : MainRoutes()

    @Serializable
    data object Bottom : MainRoutes()

    @Serializable
    data object Both : MainRoutes()
}

