package com.example.composablepdd.application

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.composablepdd.resources.book.Book
import com.example.composablepdd.resources.link.ResLinks
import com.example.composablepdd.database.getered.GetStatistics
import com.example.composablepdd.resources.region.ResourcesRegion
import com.example.composablepdd.resources.testBook.TestBook
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.database.preferences.SaveValueSettings
import com.example.composablepdd.model.DataBaseForTest
import com.example.composablepdd.screens.test.func.ButtonClick
import com.example.composablepdd.screens.test.func.ColorElementsTest
import com.example.composablepdd.database.preferences.SaveTabPosition
import com.example.composablepdd.database.getered.GetTableThemes
import com.example.composablepdd.database.getered.GetTableTicket
import com.example.composablepdd.database.preferences.SaveReminder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appContext: Application
) : ViewModel() {

    val applicationContext = appContext

    @Inject
    lateinit var getStatistics: GetStatistics

    @Inject
    lateinit var resourceRegion: ResourcesRegion

    @Inject
    lateinit var getTableTicket: GetTableTicket

    @Inject
    lateinit var getTableThemes: GetTableThemes

    @Inject
    lateinit var getDataBaseForTest: DataBaseForTest

    @Inject
    lateinit var getButtonClickTest: ButtonClick

    @Inject
    lateinit var getColorElementsTest: ColorElementsTest

    @Inject
    lateinit var getSaveTabPosition: SaveTabPosition

    @Inject
    lateinit var getSaveValueSettings: SaveValueSettings

    @Inject
    lateinit var getSaveReminder: SaveReminder

    @Inject
    lateinit var getAppTheme: AppTheme

    @Inject
    lateinit var getResLinks: ResLinks

    @Inject
    lateinit var getTestBook: TestBook

    @Inject
    lateinit var getBook: Book

}