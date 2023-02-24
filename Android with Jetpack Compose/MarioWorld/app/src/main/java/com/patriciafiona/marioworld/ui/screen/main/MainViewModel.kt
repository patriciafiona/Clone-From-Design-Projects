package com.patriciafiona.marioworld.ui.screen.main

import androidx.lifecycle.ViewModel
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.data.entities.News
import com.patriciafiona.marioworld.data.resource.DataSource.characters
import com.patriciafiona.marioworld.data.resource.DataSource.news

class MainViewModel: ViewModel() {
    fun getAllCharacters(): ArrayList<Character> = characters()

    fun getAllNews(): ArrayList<News> = news()
}