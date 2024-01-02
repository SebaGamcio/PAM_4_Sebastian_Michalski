package com.example.szczecincityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.model.local.LocalPlacesDataProvider
import com.example.szczecincityapp.ui.theme.SzczecinCityAppTheme
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceItem(
    place: Place,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(128.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(place.name),
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun PlaceItemList(
    places: List<Place>,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(places, key = { place -> place.id }) { place ->
            PlaceItem(
                place = place,
                onCardClick = onCardClick
            )
        }
    }
}

@Preview
@Composable
fun PlaceListPreview() {
    SzczecinCityAppTheme {
        PlaceItemList(
            places = LocalPlacesDataProvider.allPlaces,
            onCardClick = { /*TODO*/ }
        )
    }
}

@Preview
@Composable
fun PlaceItemPreview() {
    SzczecinCityAppTheme {
        PlaceItem(
            place = LocalPlacesDataProvider.defaultPlace,
            onCardClick = {},
        )
    }
}