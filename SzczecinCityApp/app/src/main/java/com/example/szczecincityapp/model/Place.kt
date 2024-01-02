package com.example.szczecincityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Long,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
    var category: Category = Category.CoffeeShops
)
