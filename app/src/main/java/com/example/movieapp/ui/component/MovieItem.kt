package com.example.movieapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.movieapp.data.di.MovieRemoteDataSourceModule.Companion.BASE_IMAGE_URL
import com.example.movieapp.domain.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                SubcomposeAsyncImage(
                    modifier = Modifier.size(50.dp),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(BASE_IMAGE_URL + movie.posterPath)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .networkCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "%.1f".format(movie.voteAverage),
                    style = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontWeight = FontWeight.Bold),
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = movie.overview,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        color = MaterialTheme.colorScheme.primary,
                        text = "LIKE",
                    )

                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        text = "SHARE",
                    )
                }

            }
        }
    }
}