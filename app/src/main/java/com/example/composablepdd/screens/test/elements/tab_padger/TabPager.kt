package com.example.composablepdd.screens.test.elements.tab_padger

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.R
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.screens.test.elements.tab_padger.elements.CustomProgressBarPager
import com.example.composablepdd.screens.test.elements.tab_padger.elements.MyTabsRow
import com.example.composablepdd.screens.test.elements.tab_padger.elements.PagerTopMenu
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.TabContent
import com.example.composablepdd.ui.theme.ColorTabAndProgressLine
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Composable
fun TabPager(
    appTheme: AppTheme,
    navController: NavController,
    listClickVariant: SnapshotStateList<MutableState<Int>>,
    listTabPager: MutableList<MutableState<ItemQuestions>>,
    listMistakes: MutableList<MutableState<ItemQuestions>>,
    theEndTest: MutableState<Boolean>,
    isSizeDropExamList: MutableState<Int>,
    isMistakesScreen: MutableState<Boolean>,
    timeUs: MutableState<Boolean>,
    pagerState: PagerState,
    isVisibleBook: MutableState<Boolean>,
    keyResLinks: MutableState<String>,
    mainViewModel: MainViewModel
) {

    val variantTest = mainViewModel.getDataBaseForTest.variantList.value
    val colorProgressBar = if (variantTest != 1) {
        ColorTabAndProgressLine
    } else appTheme.colorIconApp()
    val blur = when {
        theEndTest.value -> 30.dp
        isVisibleBook.value -> 50.dp
        else -> 0.dp
    }

    val drop = remember(isSizeDropExamList.value) {
        if (variantTest == 1 ) {
            when (isSizeDropExamList.value) {
                1 -> 5
                2 -> 0
                else -> 10
            }
        } else 0
    }
    val examTimeMinutes = remember { mutableIntStateOf(20) }
    val isEmptyList = listTabPager.size == 0
    if (variantTest == 4) {
        val saveTabPos = mainViewModel.getSaveTabPosition
        LaunchedEffect(Unit) {
            pagerState.scrollToPage(saveTabPos.currentPage.intValue)
        }
        LaunchedEffect(pagerState.currentPage) {
            saveTabPos.saveCurrentPage(pagerState.currentPage)
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .blur(blur)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.height(60.dp))
            PagerTopMenu(
                appTheme = appTheme,
                mainViewModel.getDataBaseForTest.getScreenName(),
                navController,
                theEndTest,
                timeUs,
                examTimeMinutes,
                variantTest
            )
            if (!isMistakesScreen.value && !isEmptyList && variantTest != 6) {
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    CustomProgressBarPager(colorProgressBar, listClickVariant, appTheme)
                }
            }
            MyTabsRow(
                lineColor = appTheme.backgroundApp(),
                listClickVariant,
                pagerState,
                listTabPager,
                isSizeDropExamList,
                isMistakesScreen,
                mainViewModel
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
        if (isEmptyList) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_empty),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .padding(bottom = 10.dp)
                    )
                Text(
                    text = "Пусто",
                    color = appTheme.colorTextApp(),
                    style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily(Font(R.font.font_main_bold)))
                )
            }
        } else {
            HorizontalPager(
                count = if (isMistakesScreen.value) listMistakes.size else listTabPager.dropLast(drop).size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                if (listTabPager.isNotEmpty() && listClickVariant.isNotEmpty()) {
                    TabContent(
                        appTheme = appTheme,
                        isVisibleBook = isVisibleBook,
                        if (isMistakesScreen.value) listMistakes[page] else listTabPager[page],
                        listClickVariant[page],
                        listClickVariant,
                        pagerState,
                        theEndTest,
                        isSizeDropExamList,
                        isMistakesScreen,
                        listMistakes,
                        listTabPager,
                        examTimeMinutes = examTimeMinutes,
                        keyResLinks = keyResLinks,
                        mainViewModel =  mainViewModel
                    )
                }
            }
        }
    }
}