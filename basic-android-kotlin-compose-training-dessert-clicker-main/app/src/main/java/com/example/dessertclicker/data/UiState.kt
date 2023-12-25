package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class UiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageID: Int = dessertList[currentDessertIndex].imageId
)
