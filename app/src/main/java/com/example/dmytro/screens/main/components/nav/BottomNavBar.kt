package com.example.dmytro.screens.main.components.nav

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.dmytro.data.models.navItems

@Composable
fun BottomNavBar(
    modifier: Modifier,
    navController: NavHostController
) {
    var selectedDest by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(modifier = modifier) {
        navItems.forEachIndexed { i, navItem ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(route = navItem.route)
                    selectedDest = i
                },
                selected = i == selectedDest,
                icon = { Text(navItem.label) }
            )
        }
    }
}