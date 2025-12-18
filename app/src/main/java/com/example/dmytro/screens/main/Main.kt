package com.example.dmytro.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dmytro.nav.main.MainRoutes
import com.example.dmytro.nav.main.mainGraph
import com.example.dmytro.screens.main.components.nav.BottomNavBar
import com.example.dmytro.screens.main.components.nav.TopNavBar

/**
 * Configuration for bar hiding behavior per screen
 */
data class BarVisibility(
    val hideTop: Boolean = false,
    val hideBottom: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val visibility = remember(currentDestination) {
        when {
            currentDestination?.hasRoute<MainRoutes.Both>() == true -> BarVisibility(
                hideTop = true,
                hideBottom = true
            )

            currentDestination?.hasRoute<MainRoutes.Top>() == true -> BarVisibility(
                hideTop = true,
                hideBottom = false
            )

            currentDestination?.hasRoute<MainRoutes.Bottom>() == true -> BarVisibility(
                hideTop = false,
                hideBottom = true
            )

            else -> BarVisibility(hideTop = false, hideBottom = false)
        }
    }

    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    var topBarHeight by remember { mutableFloatStateOf(0f) }
    var bottomBarHeight by remember { mutableFloatStateOf(0f) }
    val density = LocalDensity.current

    LaunchedEffect(currentDestination) {
        scrollBehavior.state.heightOffset = 0f
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopNavBar(
                modifier = Modifier
                    .onSizeChanged {
                        topBarHeight = it.height.toFloat()

                        // FIX: The limit must be set if EITHER bar needs to hide.
                        // We use the taller of the two or a default height to ensure the scroll "ticks".
                        if (visibility.hideTop) {
                            scrollBehavior.state.heightOffsetLimit = -topBarHeight
                        } else if (visibility.hideBottom) {
                            scrollBehavior.state.heightOffsetLimit = -bottomBarHeight
                        } else {
                            scrollBehavior.state.heightOffsetLimit = 0f
                        }
                    }
                    .offset {
                        // Only offset the Y position if hideTop is enabled
                        val y =
                            if (visibility.hideTop) scrollBehavior.state.heightOffset.toInt() else 0
                        IntOffset(0, y)
                    }
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                modifier = Modifier
                    .onSizeChanged { bottomBarHeight = it.height.toFloat() }
                    .offset {
                        // FIX: Calculate fraction based on the active limit
                        val limit = scrollBehavior.state.heightOffsetLimit
                        val fraction = if (limit < 0f && visibility.hideBottom) {
                            scrollBehavior.state.heightOffset / limit
                        } else 0f

                        IntOffset(0, (bottomBarHeight * fraction).toInt())
                    }
            )
        }
    ) { _ ->
        val currentOffset = scrollBehavior.state.heightOffset
        val limit = scrollBehavior.state.heightOffsetLimit
        val fraction = if (limit < 0f) currentOffset / limit else 0f

        val dynamicTopPadding = with(density) {
            val remaining = if (visibility.hideTop) {
                // If hideTop is false, the offset represents the bottom bar's progress
                topBarHeight * (1f - fraction)
            } else topBarHeight
            remaining.toDp()
        }

        val dynamicBottomPadding = with(density) {
            val remaining = if (visibility.hideBottom) {
                // If hideTop is false, the offset represents the bottom bar's progress
                bottomBarHeight * (1f - fraction)
            } else bottomBarHeight
            remaining.toDp()
        }

        NavHost(
            navController = navController,
            startDestination = MainRoutes.None,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dynamicTopPadding, bottom = dynamicBottomPadding)
        ) {
            mainGraph(navController = navController)
        }
    }
}