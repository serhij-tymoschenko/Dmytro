package com.example.dmytro.screens.main.components.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopNavBar(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Text(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            text = "Top Nav Bar"
        )
    }
}