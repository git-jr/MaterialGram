package com.paradoxo.materialgram.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun HomeBottomBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        screenItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) item.second.first else item.second.second,
                        contentDescription = item.first,
                    )
                },
                label = { Text(item.first) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                )
            )
        }
    }
}

val screenItems = listOf(
    Pair("Home", Pair(Icons.Filled.Home, Icons.Outlined.Home)),
    Pair("Explore", Pair(Icons.Filled.Search, Icons.Outlined.Search)),
    Pair("Notifications", Pair(Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)),
    Pair("Chat", Pair(Icons.Filled.Email, Icons.Outlined.Email))
)