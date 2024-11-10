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
import com.example.composablepdd.database.entities.ItemThemes
import javax.inject.Inject

class GetTableThemes @Inject constructor(
    private val dataBase: MainDataBase
) {
    private val fullListThemes: LiveData<List<ItemThemes>> =
        dataBase.getDao().getFullListThemes().asLiveData()

    @Composable
    fun getItemThemes(): List<ItemThemes?> {
        val getListThemes = remember { mutableStateListOf<ItemThemes?>(null) }
        val list by fullListThemes.observeAsState(emptyList())

        LaunchedEffect(list) {
            getListThemes.clear()
            getListThemes.addAll(list)
        }
        return getListThemes
    }
    @Composable
    fun getProgressItemThemes(
        index: Int
    ): Triple<MutableState<Int>, MutableState<Int>, MutableState<Float>> {
        val lifecycleOwner = LocalLifecycleOwner.current
        val getProgressThemes = remember { mutableStateOf(0) }
        val getProgressThemesSize = remember { mutableStateOf(0) }
        val getProgressThemesFloat = remember { mutableStateOf(0f) }

        fullListThemes.observe(lifecycleOwner) { list ->
            getProgressThemes.value = list[index].progress ?: 0
            getProgressThemesSize.value = list[index].allItemCounter.toInt()
            getProgressThemesFloat.value =
                getProgressThemes.value.toFloat() / getProgressThemesSize.value
        }
        return Triple(getProgressThemes, getProgressThemesSize, getProgressThemesFloat)
    }

    @Composable
    fun getProgressThemes(): Pair<MutableState<Int>, MutableState<Int>> {
        val lifecycleOwner = LocalLifecycleOwner.current

        val getProgressThemes = remember { mutableStateOf(0) }
        val getProgressThemesSize = remember { mutableStateOf(0) }

        fullListThemes.observe(lifecycleOwner) { list ->
            getProgressThemesSize.value = list.size
            getProgressThemes.value = list.count {
                it.progress == it.allItemCounter.toInt()
            }
        }
        return Pair(getProgressThemes, getProgressThemesSize)
    }
}