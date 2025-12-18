package com.example.dmytro.screens.main.components.quotes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dmytro.data.models.catQuotes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quotes() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(catQuotes) { catQuote ->
            Quote(catQuote)
        }
    }
}