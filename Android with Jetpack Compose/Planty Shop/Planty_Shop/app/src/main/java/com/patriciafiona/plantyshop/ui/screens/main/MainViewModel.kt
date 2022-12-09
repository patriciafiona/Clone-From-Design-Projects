package com.patriciafiona.plantyshop.ui.screens.main

import androidx.lifecycle.ViewModel
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.data.resource.DataResource

class MainViewModel: ViewModel() {

    private val plants = DataResource.plants()

    fun getRecommendationShop(): ArrayList<Plant>{
        plants.sortByDescending { it.rating }
        return plants.take(5) as ArrayList<Plant>
    }

}