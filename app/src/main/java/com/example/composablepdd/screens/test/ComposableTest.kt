package com.example.composablepdd.screens.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.screens.item_book.TestBook
import com.example.composablepdd.screens.test.elements.tab_padger.TabPager
import com.example.composablepdd.screens.test.elements.test_end.TestEnd
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ComposableTest(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val appTheme = mainViewModel.getAppTheme
    val pagerState = rememberPagerState()
    val variantTest = mainViewModel.getDataBaseForTest.variantList
    val timeUp = remember { mutableStateOf(false) }
    val listTabPager = mainViewModel.getDataBaseForTest.getList()
    val getClick = mainViewModel.getButtonClickTest
    val isSizeDropExamList = remember { mutableIntStateOf(0) }
    val listClickVariant = remember { mutableStateListOf<MutableState<Int>>() }
    val listMistakes = remember { mutableListOf<MutableState<ItemQuestions>>() }
    val isMistakesScreen = remember { mutableStateOf(false) }
    val theEndTest = remember { mutableStateOf(false) }
    val isVisibleBook = remember { mutableStateOf(false) }
    val keyResLinks = remember { mutableStateOf("") }
    if (listClickVariant.isEmpty()) {
        if (variantTest.value != 4){
        listClickVariant.addAll(List(listTabPager.size) { mutableIntStateOf(0) })
        } else listClickVariant.addAll(listTabPager.map { mutableStateOf(it.value.variantClickMarathon ?: 0)})
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appTheme.backgroundApp()),
        contentAlignment = Alignment.BottomCenter
    ) {
        TabPager(
            appTheme = appTheme,
            navController = navController,
            listClickVariant = listClickVariant,
            listTabPager = listTabPager,
            listMistakes = listMistakes,
            theEndTest = theEndTest,
            isSizeDropExamList = isSizeDropExamList,
            isMistakesScreen = isMistakesScreen,
            timeUs = timeUp,
            pagerState = pagerState,
            isVisibleBook = isVisibleBook,
            keyResLinks = keyResLinks,
            mainViewModel = mainViewModel
        )
        AnimatedVisibility(
            visible = theEndTest.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            TestEnd(
                listClickVariant = listClickVariant,
                listTabPager = listTabPager,
                isSizeDropExamList = isSizeDropExamList,
                timeUs = timeUp,
                mainViewModel = mainViewModel,
                pagerState = pagerState,
                onClickMistakes = { getClick.onClickMistakesScreen(theEndTest, isMistakesScreen) },
                onClickExit = { getClick.onClickExit(navController) }
            )
        }
        AnimatedVisibility(
            visible = isVisibleBook.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            TestBook(
                key = keyResLinks.value,
                mainViewModel = mainViewModel,
                cancel = {isVisibleBook.value = false}
            )
        }
    }
}

