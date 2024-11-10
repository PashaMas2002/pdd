package com.example.composablepdd.database.getered

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.database.entities.ItemThemes
import com.example.composablepdd.database.entities.ItemTicket
import javax.inject.Inject

class GetStatistics @Inject constructor(
    private val dataBase: MainDataBase
) {

    @Composable
    fun getProgressQuestions(
        viewLifecycleOwner: LifecycleOwner
    ): Triple<MutableState<Int>, MutableState<Int>, MutableState<Float>> {

        val fullListQuestions: LiveData<List<ItemQuestions>> = dataBase.getDao().getFullListQuestions().asLiveData()
        val getProgressQuestions = remember { mutableStateOf(0) }
        val getProgressQuestionsSize = remember { mutableStateOf(0) }
        val getProgressQuestionsFloat = remember { mutableStateOf(0f) }

        fullListQuestions.observe(viewLifecycleOwner) { list ->

            getProgressQuestionsSize.value = list.size
            getProgressQuestions.value = list.count { it.clickTrue == it.variantClick }
            getProgressQuestionsFloat.value = if (list.isNotEmpty()) {
                getProgressQuestions.value.toFloat() / list.size
            } else {
                0f
            }
        }
        return Triple(getProgressQuestions, getProgressQuestionsSize, getProgressQuestionsFloat)
    }

    @Composable
    fun getProgressTicket(
        viewLifecycleOwner: LifecycleOwner
    ): Triple<MutableState<Int>, MutableState<Int>, MutableState<Float>> {

        val fullListTicket: LiveData<List<ItemTicket>> = dataBase.getDao().getFullListTicket().asLiveData()
        val getProgressTicket = remember { mutableStateOf(0) }
        val getProgressTicketSize = remember { mutableStateOf(0) }
        val getProgressTicketFloat = remember { mutableStateOf(0f) }

        fullListTicket.observe(viewLifecycleOwner) { list ->

            getProgressTicketSize.value = list.size
            getProgressTicket.value = list.count { it.checkedItemCounter == 20 }
            getProgressTicketFloat.value = if (list.isNotEmpty()) {
                getProgressTicket.value.toFloat() / list.size
            } else {
                0f
            }
        }
        return Triple(getProgressTicket, getProgressTicketSize, getProgressTicketFloat)
    }

    @Composable
    fun getProgressThemes(
        viewLifecycleOwner: LifecycleOwner
    ): Triple<MutableState<Int>, MutableState<Int>, MutableState<Float>> {

        val fullListThemes: LiveData<List<ItemThemes>> = dataBase.getDao().getFullListThemes().asLiveData()
        val getProgressThemes = remember { mutableStateOf(0) }
        val getProgressThemesSize = remember { mutableStateOf(0) }
        val getProgressThemesFloat = remember { mutableStateOf(0f) }

        fullListThemes.observe(viewLifecycleOwner) { list ->

            getProgressThemesSize.value = list.size
            getProgressThemes.value = list.count { it.allItemCounter.toInt() == it.progress }
            getProgressThemesFloat.value = if (list.isNotEmpty()) {
                getProgressThemes.value.toFloat() / list.size
            } else {
                0f
            }
        }
        return Triple(getProgressThemes, getProgressThemesSize, getProgressThemesFloat)
    }
}