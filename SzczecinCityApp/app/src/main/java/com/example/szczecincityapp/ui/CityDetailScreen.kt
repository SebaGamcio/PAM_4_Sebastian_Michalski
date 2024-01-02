package com.example.szczecincityapp.ui


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.szczecincityapp.R
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.ui.theme.SzczecinCityAppTheme

@Composable
fun PlaceDetailScreen(
    cityUiState: CityUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false,
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
        ) {
            item {
                if (isFullScreen) {
                    PlaceDetailScreenTopBar(
                        onBackPressed = { onBackPressed },
                        cityUiState = cityUiState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )
                }
                PlaceDetailCard(
                    place = cityUiState.currentSelectedPlace
                )
            }
        }
    }
}

@Composable
private fun PlaceDetailCard(
    place: Place,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DetailsScreenHeader(
                place = place,
                Modifier.fillMaxWidth()
            )
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(place.description),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

}

@Composable
private fun DetailsScreenHeader(place: Place, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = 4.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(place.name)
            )
        }
    }
}


@Composable
private fun PlaceDetailScreenTopBar(
    onBackPressed: () -> Unit,
    cityUiState: CityUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackPressed,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(MaterialTheme.colorScheme.surface)
            ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = stringResource(cityUiState.currentSelectedPlace.name),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
fun PlaceDetailPreview() {
    SzczecinCityAppTheme {
        PlaceDetailScreen(
            cityUiState = CityUiState(),
            onBackPressed = { /*TODO*/ }
        )
    }
}