package com.example.composablepdd.screens.test.func

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.composablepdd.application.MainViewModel
import com.example.composablepdd.database.entities.ItemQuestions
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ButtonClick {

    fun handleButtonClick(
        variantTest: Int?,
        index: Int,
        item: MutableState<ItemQuestions>,
        clickVariant: MutableState<Int>,
        clickVariantList: SnapshotStateList<MutableState<Int>>,
        pagerState: PagerState,
        coroutineScope: CoroutineScope,
        theEndTest: MutableState<Boolean>,
        isSizeDropExamList: MutableState<Int>,
        listMistakes: MutableList<MutableState<ItemQuestions>>,
        mainViewModel: MainViewModel,
        isVisibleBottomButton: MutableState<Boolean>,
        isVisibleTextHelp: MutableState<Boolean>,
        outerListState: LazyListState,
        examTimeMinutes: MutableState<Int>,
        listQuestions: MutableList<MutableState<ItemQuestions>>
    ) {
        if (variantTest == 1) {
            buttonClickExam(
                buttonId = index + 1,
                variantTrue = item.value.clickTrue,
                clickVariant = clickVariant,
                clickVariantList = clickVariantList,
                pagerState = pagerState,
                coroutineScope = coroutineScope,
                theEndTest = theEndTest,
                isSizeDropExamList = isSizeDropExamList,
                context = mainViewModel.applicationContext,
                item = item,
                listMistakes = listMistakes,
                examTimeMinutes = examTimeMinutes
            )
        } else {
            buttonClick(
                buttonId = index + 1,
                clickVariant = clickVariant,
                clickVariantList = clickVariantList,
                item = item,
                pagerState = pagerState,
                isVisible = isVisibleBottomButton,
                isVisibleText = isVisibleTextHelp,
                lazyListState = outerListState,
                coroutineScope = coroutineScope,
                theEndTest = theEndTest,
                listMistakes = listMistakes,
                listQuestions = listQuestions,
                mainViewModel = mainViewModel
            )
        }
    }

    suspend fun onClickIcon(
        isVisible: MutableState<Boolean>,
        lazyListState: LazyListState
    ) {
        isVisible.value = true
        lazyListState.animateScrollToItem(2)
    }

    fun onClickBottomButton(
        clickVariantList: MutableList<MutableState<Int>>,
        pagerState: PagerState,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            val nextItemIndex = clickVariantList
                .drop(pagerState.currentPage)
                .indexOfFirst { it.value == 0 } + pagerState.currentPage
            val nextItemIndex2 =
                clickVariantList.indexOfFirst { it.value == 0 }

            if (nextItemIndex < pagerState.pageCount && nextItemIndex - pagerState.currentPage != -1) {
                pagerState.scrollToPage(nextItemIndex)
            } else {
                if (nextItemIndex2 != -1 && nextItemIndex2 < pagerState.pageCount) {
                    pagerState.scrollToPage(nextItemIndex2)
                }
            }
        }
    }


    fun onClickMistakesScreen(
        theEndTest: MutableState<Boolean>,
        isMistakes: MutableState<Boolean>,
    ) {
        isMistakes.value = true
        theEndTest.value = false
    }

    fun onClickExit(
        navController: NavController
    ) {
        navController.navigate("MAIN_SCREEN") {
            popUpTo("MAIN_SCREEN") { inclusive = true }
        }
    }

    private fun buttonClick(
        buttonId: Int,
        clickVariant: MutableState<Int>,
        clickVariantList: SnapshotStateList<MutableState<Int>>,
        item: MutableState<ItemQuestions>,
        pagerState: PagerState,
        isVisible: MutableState<Boolean>,
        isVisibleText: MutableState<Boolean>,
        lazyListState: LazyListState,
        coroutineScope: CoroutineScope,
        theEndTest: MutableState<Boolean>,
        listMistakes: MutableList<MutableState<ItemQuestions>>,
        listQuestions: MutableList<MutableState<ItemQuestions>>,
        mainViewModel: MainViewModel
    ) {
        coroutineScope.launch {
            val variantList = mainViewModel.getDataBaseForTest.variantList.value
            val variantNumList = mainViewModel.getDataBaseForTest.numList
            if (clickVariant.value == 0) {
                clickVariant.value = buttonId
                if (buttonId != item.value.clickTrue) {
                    val updatedItem = item.value.copy(variantClick = buttonId)
                    listMistakes.add(
                        mutableStateOf(updatedItem)
                    )
                }
                if (variantList == 4) {
                    val newItem = item.value.copy(variantClickMarathon = buttonId)
                    mainViewModel.getDataBaseForTest.updateItemQuestions(newItem)
                }
                if (buttonId == item.value.clickTrue) {
                    val nextItemIndex = clickVariantList
                        .drop(pagerState.currentPage)
                        .indexOfFirst { it.value == 0 } + pagerState.currentPage
                    val nextItemIndex2 =
                        clickVariantList.indexOfFirst { it.value == 0 }
                    if (nextItemIndex < pagerState.pageCount && nextItemIndex - pagerState.currentPage != -1) {
                        pagerState.scrollToPage(nextItemIndex)
                    } else {
                        if (nextItemIndex2 != -1 && nextItemIndex2 < pagerState.pageCount) {
                            pagerState.scrollToPage(nextItemIndex2)
                        } else {
                            theEndTest.value = true
                            mainViewModel.viewModelScope.launch {
                                if (variantList !in listOf(1, 4)) {
                                    mainViewModel.getDataBaseForTest.updateListQuestions(
                                        listQuestions.mapIndexed { index, item ->
                                            item.value.copy(variantClick = clickVariantList[index].value)
                                        }
                                    )
                                    if (variantList == 2) {
                                        val value = listQuestions.filterIndexed { index, item ->
                                            item.value.clickTrue == clickVariantList[index].value
                                        }.size
                                        mainViewModel.getDataBaseForTest
                                            .updateValueByIdItemTicket(
                                                id = variantNumList,
                                                value = value,
                                                mistakes = listQuestions.size - value
                                            )
                                    }
                                    if (variantList == 3) {
                                        mainViewModel.getDataBaseForTest
                                            .updateValueByIdItemThemes(
                                                id = variantNumList,
                                                value = listQuestions.filterIndexed { index, item ->
                                                    item.value.clickTrue == clickVariantList[index].value
                                                }.size
                                            )
                                    }
                                }
                            }

                        }
                    }
                } else if (clickVariantList.filter { it.value == 0 }.isNotEmpty()) {
                    isVisible.value = true
                    onClickIcon(isVisibleText, lazyListState)
                } else {
                    theEndTest.value = true
                    mainViewModel.viewModelScope.launch {
                        if (variantList !in listOf(1, 4)) {
                            mainViewModel.getDataBaseForTest.updateListQuestions(
                                listQuestions.mapIndexed { index, item ->
                                    item.value.copy(variantClick = clickVariantList[index].value)
                                }
                            )
                            if (variantList == 2) {
                                val value = listQuestions.filterIndexed { index, item ->
                                    item.value.clickTrue == clickVariantList[index].value
                                }.size
                                mainViewModel.getDataBaseForTest
                                    .updateValueByIdItemTicket(
                                        id = variantNumList,
                                        value = value,
                                        mistakes = listQuestions.size - value
                                    )
                            }
                            if (variantList == 3) {
                                mainViewModel.getDataBaseForTest
                                    .updateValueByIdItemThemes(
                                        id = variantNumList,
                                        value = listQuestions.filterIndexed { index, item ->
                                            item.value.clickTrue == clickVariantList[index].value
                                        }.size
                                    )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun buttonClickExam(
        buttonId: Int,
        variantTrue: Int,
        clickVariant: MutableState<Int>,
        clickVariantList: SnapshotStateList<MutableState<Int>>,
        pagerState: PagerState,
        coroutineScope: CoroutineScope,
        theEndTest: MutableState<Boolean>,
        isSizeDropExamList: MutableState<Int>,
        context: Context,
        item: MutableState<ItemQuestions>,
        examTimeMinutes: MutableState<Int>,
        listMistakes: MutableList<MutableState<ItemQuestions>>
    ) {

        coroutineScope.launch {
            if (clickVariant.value == 0) {
                clickVariant.value = buttonId

                if (buttonId != variantTrue) {
                    val updatedItem = item.value.copy(variantClick = buttonId)
                    listMistakes.add(
                        mutableStateOf(updatedItem)
                    )
                    if (clickVariantList.filter { it.value != 0 }.size <= 20){
                        isSizeDropExamList.value++
                    }
                }
                if (clickVariantList.filter { it.value != 0 }.size == 20) {
                    if (isSizeDropExamList.value == 1) {
                        Toast.makeText(
                            context,
                            "Добавленно дополнительно 5 вопросов",
                            Toast.LENGTH_SHORT
                        ).show()
                        examTimeMinutes.value += 5
                    } else if (isSizeDropExamList.value == 2) {
                        Toast.makeText(
                            context,
                            "Добавленно дополнительно 10 вопросов",
                            Toast.LENGTH_SHORT
                        ).show()
                        examTimeMinutes.value += 10
                    }
                }

                val nextItemIndex = clickVariantList
                    .drop(pagerState.currentPage)
                    .indexOfFirst { it.value == 0 } + pagerState.currentPage
                val nextItemIndex2 =
                    clickVariantList.indexOfFirst { it.value == 0 }


                if (nextItemIndex < pagerState.pageCount && nextItemIndex - pagerState.currentPage != -1) {
                    pagerState.scrollToPage(nextItemIndex)
                } else {
                    coroutineScope.launch {
                        delay(500)
                        if (nextItemIndex2 != -1 && nextItemIndex2 < pagerState.pageCount) {
                            pagerState.scrollToPage(nextItemIndex2)
                        } else {

                            theEndTest.value = true
                            examTimeMinutes.value = 0

                        }
                    }
                }
            }
        }
    }
}