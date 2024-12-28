package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.screen.favorite.FavoriteScreen
import com.example.movieapp.ui.screen.home.HomeScreen

@Composable
fun MainScreen() {
    val currentIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val tabs = listOf(
        stringResource(R.string.tab_title_films),
        stringResource(R.string.tab_title_favorites),
    )

    Column {
        TabRow(
            containerColor = MaterialTheme.colorScheme.background,
            selectedTabIndex = currentIndex.intValue,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier.padding(12.dp),
                    selected = currentIndex.intValue == index,
                    onClick = {
                        currentIndex.intValue = index
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                ) {
                    Text(tab)
                }
            }
        }

        when (currentIndex.intValue) {
            0 -> HomeScreen()
            1 -> FavoriteScreen()
        }
    }
}