package com.example.dmytro.data.models

import com.example.dmytro.nav.main.MainRoutes

data class NavItem(
    val label: String,
    val route: MainRoutes
)

val navItems = listOf(
    NavItem("None", MainRoutes.None),
    NavItem("Top", MainRoutes.Top),
    NavItem("Bottom", MainRoutes.Bottom),
    NavItem("Both", MainRoutes.Both),
)