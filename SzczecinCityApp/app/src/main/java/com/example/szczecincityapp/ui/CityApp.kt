package com.example.szczecincityapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.szczecincityapp.model.Category
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.ui.utils.CityContentType
import com.example.szczecincityapp.ui.utils.CityNavigationType

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val navigationType: CityNavigationType
    val contentType: CityContentType
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = CityNavigationType.NAVIGATION_RAIL
            contentType = CityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = CityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = CityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = CityNavigationType.BOTTOM_NAVIGATION
            contentType = CityContentType.LIST_ONLY
        }
    }
    CityHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        cityUiState = cityUiState,
        onTabPressed = { category: Category ->
            viewModel.updateCurrentCategory(category = category)
            viewModel.resetCategoryScreenStates()
        },
        onPlacePressed = { place: Place ->
            viewModel.updateDetailsScreenStates(
                place = place
            )
        },
        onDetailScreenBackPressed = { viewModel.resetCategoryScreenStates() },
        modifier = modifier
    )

}