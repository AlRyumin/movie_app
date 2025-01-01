package com.example.movieapp.ui.utils

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToEnd(): Boolean {
    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
    val totalItemsCount = layoutInfo.totalItemsCount
    return lastVisibleItemIndex == totalItemsCount - 1
}