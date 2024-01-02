package com.example.szczecincityapp.ui

import com.example.szczecincityapp.model.Category
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.model.local.LocalPlacesDataProvider

data class CityUiState(
    val categories: Map<Category, List<Place>> = emptyMap(),
    val currentCategory: Category = Category.Parks,
    val currentSelectedPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val isShowingHomePage: Boolean = true
){
    val currentCategories: List<Place> by lazy { categories[currentCategory]!! }
}