package com.paradoxo.materialgram.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.paradoxo.materialgram.sampleData.posts
import com.paradoxo.materialgram.ui.home.HomeAppBar
import com.paradoxo.materialgram.ui.home.HomeBottomBar
import com.paradoxo.materialgram.ui.home.HomeContent
import com.paradoxo.materialgram.ui.home.HomeFAB
import com.paradoxo.materialgram.ui.theme.MaterialGramTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialGramTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}


@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        floatingActionButton = {
            HomeFAB()
        },
        bottomBar = {
            HomeBottomBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HomeContent(posts)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialGramTheme {
        HomeScreen()
    }
}



