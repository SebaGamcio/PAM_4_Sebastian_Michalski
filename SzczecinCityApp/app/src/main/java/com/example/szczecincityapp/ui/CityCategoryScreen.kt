package com.example.szczecincityapp.ui

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.szczecincityapp.R
import com.example.szczecincityapp.model.Category
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.ui.theme.SzczecinCityAppTheme
import com.example.szczecincityapp.ui.utils.CityContentType
import com.example.szczecincityapp.ui.utils.CityNavigationType

@Composable
fun CityHomeScreen(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: (Category) -> Unit,
    onPlacePressed: (Place) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allCategories: List<CategoryItemContent> = listOf(
        CategoryItemContent(
            category = Category.Parks,
            icon = R.drawable.forest_24px,
            text = stringResource(R.string.category_2)
        ),
        CategoryItemContent(
            category = Category.CoffeeShops,
            icon = R.drawable.coffee_24px,
            text = stringResource(R.string.category_1)
        ),
        CategoryItemContent(
            category = Category.Restaurants,
            icon = R.drawable.restaurant_24px,
            text = stringResource(R.string.category_3)
        )
    )
    if (navigationType == CityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(240.dp)) {
                    NavigationDrawerContent(
                        selectedCategory = cityUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        categoryItemContentList = allCategories,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .padding(12.dp)
                    )
                }
            }
        ) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onPlacePressed = onPlacePressed,
                categoryItemContentList = allCategories
            )
        }
    } else {
        if (cityUiState.isShowingHomePage) {
            CityAppContent(
                navigationType = navigationType,
                contentType = contentType,
                cityUiState = cityUiState,
                onTabPressed = onTabPressed,
                onPlacePressed = onPlacePressed,
                categoryItemContentList = allCategories
            )
        } else {
            PlaceDetailScreen(
                cityUiState = cityUiState,
                onBackPressed = onDetailScreenBackPressed,
                isFullScreen = true,
                modifier = modifier
            )
        }
    }

}


@Composable
private fun NavigationDrawerContent(
    selectedCategory: Category,
    onTabPressed: ((Category) -> Unit),
    categoryItemContentList: List<CategoryItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
        )
        for (categoryItem in categoryItemContentList) {
            NavigationDrawerItem(
                label = {
                        Text(
                            text = categoryItem.text,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                },
                icon = {
                       Image(
                           painter = painterResource(categoryItem.icon),
                           contentDescription = categoryItem.text
                       )
                },
                selected = selectedCategory == categoryItem.category,
                onClick = { onTabPressed(categoryItem.category) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.city_name),
            modifier = Modifier,
            fontSize = 25.sp
        )
    }
}


@Composable
private fun CityAppContent(
    navigationType: CityNavigationType,
    contentType: CityContentType,
    cityUiState: CityUiState,
    onTabPressed: ((Category) -> Unit),
    onPlacePressed: (Place) -> Unit,
    categoryItemContentList: List<CategoryItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == CityNavigationType.NAVIGATION_RAIL) {
                CityNavigationRail(
                    currentCategory = cityUiState.currentCategory,
                    onTabPressed = onTabPressed,
                    categoryItemContent = categoryItemContentList)
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == CityContentType.LIST_AND_DETAIL) {
                    CityListAndDetailContent(
                        cityUiState = cityUiState,
                        onPlacePressed = onPlacePressed,
                        modifier = Modifier.weight(1f)
                        )
                } else {
                    CityListOnly(
                        cityUiState = cityUiState,
                        onPlacePressed = onPlacePressed,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    )
                }
                AnimatedVisibility(visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION) {
                    CityBottomNavigationBar(
                        currentCategory = cityUiState.currentCategory,
                        onTabPressed = onTabPressed,
                        categoryItemContentList = categoryItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CityNavigationRail(
    currentCategory: Category,
    onTabPressed: ((Category) -> Unit),
    categoryItemContent: List<CategoryItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (categoryItem in categoryItemContent) {
            NavigationRailItem(
                selected = currentCategory == categoryItem.category,
                onClick = { onTabPressed(categoryItem.category) },
                icon = {
                    Image(
                        painter = painterResource(categoryItem.icon),
                        contentDescription = categoryItem.text
                    )
                })
        }
    }
}

@Composable
fun CityBottomNavigationBar(
    currentCategory: Category,
    onTabPressed: ((Category) -> Unit),
    categoryItemContentList: List<CategoryItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (categoryItem in categoryItemContentList) {
            NavigationBarItem(
                selected = currentCategory == categoryItem.category,
                onClick = { onTabPressed(categoryItem.category) },
                icon = {
                    Image(
                        painter = painterResource(categoryItem.icon),
                        contentDescription = categoryItem.text)
                })
        }
    }
}

@Composable
fun CityListOnly(
    cityUiState: CityUiState,
    onPlacePressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val palces = cityUiState.currentCategories

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            CityHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
        items(palces, key = { place -> place.id }) {place ->
            PlaceItem(
                place = place,
                onCardClick = {
                    onPlacePressed(place)
                }
            )
        }

    }
}

@Composable
fun CityListAndDetailContent(
    cityUiState: CityUiState,
    onPlacePressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val places = cityUiState.currentCategories
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = 16.dp,
                    top = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(places, key = { place -> place.id }) { place ->
                PlaceItem(
                    place = place,
                    onCardClick = { onPlacePressed(place) }
                )
            }
        }
        val activity = LocalContext.current as Activity
        PlaceDetailScreen(
            cityUiState = cityUiState,
            onBackPressed = { activity.finish() },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CityHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.city_name),
        )
    }
}

@Preview
@Composable
fun CityListAndDetailPreview() {
    SzczecinCityAppTheme {
        CityListOnly(
            cityUiState = CityUiState(),
            onPlacePressed = { })
    }
}


data class CategoryItemContent(
    val category: Category,
    @DrawableRes val icon: Int,
    val text: String
)





