package com.example.szczecincityapp.ui

import androidx.lifecycle.ViewModel
import com.example.szczecincityapp.model.Category
import com.example.szczecincityapp.model.Place
import com.example.szczecincityapp.model.local.LocalPlacesDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        val categories: Map<Category, List<Place>> =
            LocalPlacesDataProvider.allPlaces.groupBy { it.category }
        _uiState.value =
            CityUiState(
                categories = categories,
                currentSelectedPlace = categories[Category.Parks]?.get(0) ?: LocalPlacesDataProvider.defaultPlace
            )
    }

    fun updateDetailsScreenStates(place: Place) {
        _uiState.update {
            it.copy(
                currentSelectedPlace = place,
                isShowingHomePage = false
            )
        }
    }

    fun resetCategoryScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedPlace = it.categories[it.currentCategory]?.get(0) ?: LocalPlacesDataProvider.defaultPlace,
                isShowingHomePage = true
            )
        }
    }

    fun updateCurrentCategory(category: Category) {
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }

}