package com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.AnimTextHelp
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.BottomButtonsTabContent
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.ButtonFavorites
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.ButtonTabContent
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.ItemImage
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.ItemTextDescription
import com.example.composablepdd.screens.test.elements.tab_padger.elements.tab_content.element.ItemTheEnd
import com.example.composablepdd.screens.test.func.getBitmap
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@Composable
fun TabContent(
    appTheme: AppTheme,
    isVisibleBook: MutableState<Boolean>,
    item: MutableState<ItemQuestions>,
    clickVariant: MutableState<Int>,
    clickVariantList: SnapshotStateList<MutableState<Int>>,
    pagerState: PagerState,
    theEndTest: MutableState<Boolean>,
    isSizeDropExamList: MutableState<Int>,
    isMistakesScreen: MutableState<Boolean>,
    listMistakes: MutableList<MutableState<ItemQuestions>>,
    listQuestions: MutableList<MutableState<ItemQuestions>>,
    examTimeMinutes: MutableState<Int>,
    keyResLinks: MutableState<String>,
    mainViewModel: MainViewModel
) {
    val bitmap = getBitmap(item.value)
    val coroutineScope = rememberCoroutineScope()
    val isVisibleTextHelp = remember { mutableStateOf(false) }
    val isVisibleBottomButton = remember { mutableStateOf(false) }
    val variantTest = mainViewModel.getDataBaseForTest.variantList.value
    val texts = getListTextsButtons(item)
    val outerListState = rememberLazyListState()
    LaunchedEffect(outerListState) {
        if (outerListState.layoutInfo.visibleItemsInfo.isEmpty() || outerListState.firstVisibleItemIndex != 0) {
            outerListState.animateScrollToItem(index = 0,)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = outerListState,
            contentPadding = PaddingValues(bottom = 210.dp)
        ) {
            item {
                ItemImage(bitmap)
            }
            item {
                ItemTextDescription(text = item.value.name, color = appTheme.colorTextApp())
            }
            if (!isMistakesScreen.value) {
                item {
                    texts.forEachIndexed { index, text ->
                        text?.let {
                            ButtonTabContent(
                                text = it,
                                color =
                                mainViewModel.getColorElementsTest.handleButtonClick(
                                    variantTest = variantTest,
                                    buttonId = index + 1,
                                    clickVariant = clickVariant.value,
                                    clickTrue = item.value.clickTrue,
                                    tabColor = mainViewModel.getAppTheme.colorTabExam()
                                ),
                                onClick = {
                                    mainViewModel.getButtonClickTest.handleButtonClick(
                                        variantTest = variantTest,
                                        index = index,
                                        item = item,
                                        clickVariant = clickVariant,
                                        clickVariantList = clickVariantList,
                                        pagerState = pagerState,
                                        coroutineScope = coroutineScope,
                                        theEndTest = theEndTest,
                                        isSizeDropExamList = isSizeDropExamList,
                                        listMistakes = listMistakes,
                                        mainViewModel = mainViewModel,
                                        isVisibleBottomButton = isVisibleBottomButton,
                                        isVisibleTextHelp = isVisibleTextHelp,
                                        outerListState = outerListState,
                                        listQuestions = listQuestions,
                                        examTimeMinutes = examTimeMinutes
                                    )
                                }
                            )
                        }
                    }
                }
            } else {
                item {
                    ItemTheEnd(appTheme, item.value, texts)
                }
            }
            item {
                ButtonFavorites(
                    appTheme = appTheme,
                    isChecked = item.value.checkFlag,
                    onClick = {
                        val updateItem = item.value.copy(checkFlag = !item.value.checkFlag)
                        mainViewModel.getDataBaseForTest.updateItemQuestions(updateItem)
                        item.value = updateItem
                        coroutineScope.launch {
                            if (variantTest == 6) {
                                clickVariantList.add(mutableIntStateOf(0))
                                clickVariantList.remove(clickVariant)
                                isVisibleTextHelp.value = false
                            }
                        }
                    },
                    variantTest = variantTest
                )
            }
            item {
                AnimTextHelp(
                    index = item.value.id!!,
                    appTheme = appTheme,
                    isVisibleTextHelp = isVisibleTextHelp,
                    isMistakesScreen = isMistakesScreen,
                    text = item.value.textHelp!!,
                    isVisibleBook = isVisibleBook,
                    keyResLinks = keyResLinks,
                    mainViewModel = mainViewModel
                )
            }
        }
        if (variantTest != 1) {
            BottomButtonsTabContent(
                color = appTheme.colorIconApp(),
                isVisible = isVisibleBottomButton,
                isVisibleIcon = isVisibleTextHelp,
                onClickIcon = {
                    coroutineScope.launch {
                        mainViewModel.getButtonClickTest.onClickIcon(
                            isVisibleTextHelp,
                            outerListState
                        )
                    }
                },
                onClickCard = {
                    mainViewModel.getButtonClickTest.onClickBottomButton(
                        clickVariantList, pagerState, coroutineScope
                    )
                }
            )
        }
    }
}


private fun getListTextsButtons(item: MutableState<ItemQuestions>): List<String?> {
    return listOf(
        item.value.text1,
        item.value.text2,
        item.value.text3,
        item.value.text4,
        item.value.text5
    )
}