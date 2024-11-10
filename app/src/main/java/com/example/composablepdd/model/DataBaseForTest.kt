package com.example.composablepdd.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.room.withTransaction
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.entities.ItemQuestions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataBaseForTest @Inject constructor(
    private val dataBase: MainDataBase,
) {
    private fun <T> List<T>.randomSample(size: Int): List<T> {
        if (size >= this.size) return this
        return this.shuffled().take(size)
    }

    private val fullListQuestions = dataBase.getDao().getFullListQuestions()
    private val fullListQuestionsOrder = dataBase.getDao().getFullListQuestionsOrder()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    var variantList = mutableStateOf<Int?>(null)
    var numList: Int = 1


    fun getScreenName(): String {

        return if (variantList.value != null) {
            when (variantList.value) {
                1 -> "Экзамен"
                2 -> "Билет: $numList"
                3 -> "Тема: $numList"
                4 -> "Марафон"
                5 -> "Мои ошибки"
                6 -> "Избранные"
                else -> ""
            }
        } else ""
    }

    @Composable
    fun getList(): MutableList<MutableState<ItemQuestions>> {

        return if (variantList.value != null) {
            when (variantList.value) {
                1 -> {
                    getListExam()
                }

                2 -> {
                    getListTicket(numList)
                }

                3 -> {
                    getListTheme(numList)
                }

                4 -> {
                    getListMarathon()
                }

                5 -> {
                    getListMistakes()
                }

                6 -> {
                    getListFavorites()
                }

                else -> mutableListOf()
            }
        } else mutableListOf()
    }

    fun updateItemQuestions(item: ItemQuestions) {
        coroutineScope.launch {
            dataBase.getDao().updateItemQuestions(item)
        }
    }

    fun updateValueByIdItemTicket(id: Int, value: Int, mistakes: Int) {
        coroutineScope.launch {
            dataBase.getDao().updateValueByIdItemTicket(id, value, mistakes)
        }
    }

    fun updateValueByIdItemThemes(id: Int, value: Int) {
        coroutineScope.launch {
            dataBase.getDao().updateValueByIdItemThemes(id, value)
        }
    }

    fun updateListQuestions(list: List<ItemQuestions>) {
        coroutineScope.launch {
            dataBase.getDao().updateListQuestions(list)
        }
    }

    fun updateListQuestionsMarathon() {
        val ids = (1..800).toList()
        val idsOrder = ids.shuffled()

        // Генерация списка пар (id, новый порядок)
        val idValuePairs = ids.zip(idsOrder)

        coroutineScope.launch {
            dataBase.withTransaction {
                // Вызов метода DAO для пакетного обновления
                val idList = idValuePairs.map { it.first }
                val newValues = idValuePairs.map { it.second }
                dataBase.getDao().updateValuesByIds(idList, newValues)
            }
        }
    }


    @Composable
    private fun getFilteredList(
        filter: (ItemQuestions) -> Boolean
    ): MutableList<MutableState<ItemQuestions>> {
        val listAll by fullListQuestions.collectAsState(emptyList())
        return remember(listAll) {
            mutableListOf<MutableState<ItemQuestions>>().apply {
                val filteredItems = listAll.filter { filter(it) }
                filteredItems.forEach { item ->
                    add(mutableStateOf(item))
                }
            }
        }
    }

    @Composable
    private fun getListExam(): MutableList<MutableState<ItemQuestions>> {
        val listAll by fullListQuestions.collectAsState(emptyList())
        return remember(listAll.size) {
            mutableListOf<MutableState<ItemQuestions>>().apply {
                val listSorted = listAll.randomSample(30)
                listSorted.forEach { item ->
                    add(mutableStateOf(item))
                }
            }
        }
    }

    @Composable
    private fun getListTicket(numTicket: Int): MutableList<MutableState<ItemQuestions>> {
        return getFilteredList { it.listNumber == numTicket }
    }

    @Composable
    private fun getListTheme(numTheme: Int): MutableList<MutableState<ItemQuestions>> {
        return getFilteredList {
            it.numberListThemes == numTheme || it.numberListThemes2 == numTheme
        }
    }

    @Composable
    private fun getListMistakes(): MutableList<MutableState<ItemQuestions>> {
        return getFilteredList {
            it.clickTrue != it.variantClick && it.variantClick != null
        }
    }

    @Composable
    private fun getListFavorites(): MutableList<MutableState<ItemQuestions>> {
        return getFilteredList { it.checkFlag }
    }

    @Composable
    private fun getListMarathon(): MutableList<MutableState<ItemQuestions>> {
        val listAll by fullListQuestionsOrder.collectAsState(emptyList())
        return remember(listAll.size) {
            mutableListOf<MutableState<ItemQuestions>>().apply {
                listAll.forEach { item ->
                    add(mutableStateOf(item))
                }
            }
        }
    }

    val isMarathonRestarted: Flow<Boolean> = dataBase.getDao()
        .getItemCountQuestionsMarathonCheck()
        .map { count -> count == 0 }
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), true)

}