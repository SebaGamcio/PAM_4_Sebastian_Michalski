package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val dessertUiState: StateFlow<UiState> = _uiState

    fun onDessertClick() {
        _uiState.update { sweetUiState ->
            val dessertSold = sweetUiState.dessertsSold + 1
            val nextDessert = specifiedDessertIndex(dessertSold)
            sweetUiState.copy(
                currentDessertIndex = nextDessert,
                revenue = sweetUiState.revenue + sweetUiState.currentDessertPrice,
                dessertsSold = dessertSold,
                currentDessertImageID = dessertList[nextDessert].imageId,
                currentDessertPrice = dessertList[nextDessert].price
            )
        }
    }

    private fun specifiedDessertIndex(dessertSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertSold >= dessertList[index].startProductionAmount){
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }

}