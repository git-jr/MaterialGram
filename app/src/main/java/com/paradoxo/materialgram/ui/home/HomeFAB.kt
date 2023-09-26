package com.paradoxo.materialgram.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HomeFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}