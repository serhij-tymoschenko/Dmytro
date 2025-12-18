package com.example.dmytro.screens.main.components.quotes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dmytro.data.models.CatQuote

@Composable
fun Quote(catQuote: CatQuote) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = catQuote.quote
    )
}