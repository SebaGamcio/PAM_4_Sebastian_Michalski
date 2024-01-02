package com.example.szczecincityapp.model.local

import com.example.szczecincityapp.R
import com.example.szczecincityapp.model.Category
import com.example.szczecincityapp.model.Place

object LocalPlacesDataProvider {

    val allPlaces: List<Place> = listOf(
        Place(
            id = 0L,
            name = R.string.park_name_1,
            description = R.string.park_des_1,
            image = R.drawable.park_kasprowicza,
            category = Category.Parks
        ),
        Place(
            id = 1L,
            name = R.string.park_name_2,
            description = R.string.park_des_2,
            image = R.drawable.puszcza_bukowa,
            category = Category.Parks
        ),
        Place(
            id = 2L,
            name = R.string.park_name_3,
            description = R.string.park_des_3,
            image = R.drawable.jezioro_szmaragdowe,
            category = Category.Parks
        ),
        Place(
            id = 3L,
            name = R.string.coffee_name_1,
            description = R.string.coffee_des_1,
            image = R.drawable.bajgle_krola_jana
        ),
        Place(
            id = 4L,
            name = R.string.coffee_name_2,
            description = R.string.coffee_des_2,
            image = R.drawable.caffe_22
        ),
        Place(
            id = 5L,
            name = R.string.coffee_name_3,
            description = R.string.coffee_des_3,
            image = R.drawable.alternatywnie
        ),
        Place(
            id = 6L,
            name = R.string.restaurant_name_1,
            description = R.string.restaurant_des_1,
            image = R.drawable.tokyo_suchi_n_grill,
            category = Category.Restaurants
        ),
        Place(
            id = 7L,
            name = R.string.restaurant_name_2,
            description = R.string.restaurant_des_2,
            image = R.drawable.park_restauracja,
            category = Category.Restaurants
        ),
        Place(
            id = 8L,
            name = R.string.restaurant_name_3,
            description = R.string.restaurant_des_3,
            image = R.drawable.zakotwiczony_oysters,
            category = Category.Restaurants
        ),
    )

    fun get(id: Long): Place? {
        return allPlaces.firstOrNull { it.id == id }
    }

    val defaultPlace: Place
        get() = allPlaces[0]

}