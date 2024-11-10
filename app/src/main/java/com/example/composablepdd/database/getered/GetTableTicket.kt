package com.example.composablepdd.database.getered

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.entities.ItemTicket
import javax.inject.Inject

class GetTableTicket @Inject constructor(
    private val dataBase: MainDataBase,
) {
    private val fullListTicket: LiveData<List<ItemTicket>> =
        dataBase.getDao().getFullListTicket().asLiveData()


    @Composable
    fun getItemTicket(): List<ItemTicket?> {
        val getListTicket = remember { mutableStateListOf<ItemTicket?>(null) }
        val list by fullListTicket.observeAsState(emptyList())

        LaunchedEffect(list) {
            getListTicket.clear()
            getListTicket.addAll(list)
        }
        return getListTicket
    }

    @Composable
    fun getProgressItemTicket(
        index: Int
    ): Triple<MutableState<Int>, MutableState<Int>, MutableState<Float>> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val getProgressTicket = remember { mutableStateOf(0) }
        val getProgressTicketMistake = remember { mutableStateOf(0) }
        val getProgressTicketFloat = remember { mutableStateOf(0f) }

        fullListTicket.observe(lifecycleOwner) { list ->
            getProgressTicket.value = list[index].checkedItemCounter ?: 0
            getProgressTicketMistake.value = list[index].errorQuality ?: 0
            getProgressTicketFloat.value =
                getProgressTicket.value.toFloat() / 20
        }
        return Triple(getProgressTicket, getProgressTicketMistake, getProgressTicketFloat)
    }

    @Composable
    fun getProgressTicket(): Pair<MutableState<Int>, MutableState<Int>> {
        val lifecycleOwner = LocalLifecycleOwner.current

        val getProgressTicket = remember { mutableStateOf(0) }
        val getProgressTicketSize = remember { mutableStateOf(0) }

        fullListTicket.observe(lifecycleOwner) { list ->
            getProgressTicket.value = list.count { it.checkedItemCounter == it.allItemCounter }
            getProgressTicketSize.value = list.size
        }
        return Pair(getProgressTicket, getProgressTicketSize)
    }
}