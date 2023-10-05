@file:OptIn(ExperimentalMaterial3Api::class)

package com.paradoxo.materialgram.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun HomeSearchAppBar(scrollBehavior: TopAppBarScrollBehavior) {

    TopAppBar(
        title = {},
        scrollBehavior = scrollBehavior,
        actions = {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
                    .shadow(
                        1.dp, CircleShape,
                        spotColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Text(
                    text = "Pesquisar",
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(16.dp))

                    AsyncImageWithShimmer(
                        data = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(3).jpeg",
                        contentDescription = "imagem de perfil",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )

}

@ExperimentalMaterial3Api
@Composable
fun HomeTabsAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSelectedTab: (Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Home", "Reels")

    TopAppBar(
        title = {},
        scrollBehavior = scrollBehavior,
        actions = {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                            onSelectedTab(index)
                        },
                        text = { Text(text = title) }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

