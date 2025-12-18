package com.example.dmytro.nav.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.dmytro.screens.main.hideables.both.BothHideable
import com.example.dmytro.screens.main.hideables.bottom.BottomHideable
import com.example.dmytro.screens.main.hideables.none.NoneHideable
import com.example.dmytro.screens.main.hideables.top.TopHideable

fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    composable<MainRoutes.None> {
        NoneHideable()
    }

    composable<MainRoutes.Top> {
        TopHideable()
    }

    composable<MainRoutes.Bottom> {
        BottomHideable()
    }

    composable<MainRoutes.Both> {
        BothHideable()
    }
}