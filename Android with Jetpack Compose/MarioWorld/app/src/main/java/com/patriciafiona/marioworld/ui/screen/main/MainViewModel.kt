package com.patriciafiona.marioworld.ui.screen.main

import androidx.lifecycle.ViewModel
import com.patriciafiona.marioworld.data.MenuType
import com.patriciafiona.marioworld.data.entities.Character
import com.patriciafiona.marioworld.data.entities.News
import com.patriciafiona.marioworld.data.resource.DataSource
import com.patriciafiona.marioworld.data.resource.DataSource.characters
import com.patriciafiona.marioworld.data.resource.DataSource.news

data class UiState(
    val currentMenu: MenuType = MenuType.News,
    val currentSelectedCharacter: Character = DataSource.defaultCharacter,
    val currentSelectedNews: News = DataSource.defaultNews,
    val isShowingListPage: Boolean = true,
    val isShowingHomepage: Boolean = true
)

class MainViewModel: ViewModel() {
    fun getAllCharacters(): ArrayList<Character> = characters()

    fun getAllNews(): ArrayList<News> = news()
}