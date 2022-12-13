package com.patriciafiona.plantyshop.ui.screens.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.patriciafiona.plantyshop.data.entity_and_enum.Explore
import com.patriciafiona.plantyshop.data.entity_and_enum.Plant
import com.patriciafiona.plantyshop.data.resource.DataResource

class DetailViewModel: ViewModel() {

    fun getAllColors(): ArrayList<Color> {
        return DataResource.colors()
    }

}