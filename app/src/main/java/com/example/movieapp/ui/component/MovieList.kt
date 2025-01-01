package com.example.movieapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.ui.screen.home.ContentState
import com.example.movieapp.ui.utils.isScrolledToEnd

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    contentState: ContentState<Map<String, List<Movie>>>,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            listState.isScrolledToEnd()
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            contentState.loadMore()
        }
    }

    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = contentState.isRefreshing,
        onRefresh = { contentState.refresh() },
        modifier = modifier,
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
        ) {
            contentState.data.forEach { (monthYear, movieList) ->
                item {
                    MovieHeader(title = monthYear)
                }
                items(movieList) { movie ->
                    MovieItem(movie = movie, modifier = Modifier.padding(horizontal = 16.dp))
                    VerticalDivider(modifier = Modifier.height(8.dp))
                }
            }
        }

        if (contentState.isLoadingMore) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(40.dp)
                        .size(100.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 5.dp,
                )
            }
        }
    }
}

@Composable
fun MovieHeader(title: String) {
    Text(
        title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.titleSmall
    )
}
