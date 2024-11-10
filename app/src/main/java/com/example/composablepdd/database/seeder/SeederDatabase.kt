package com.example.composablepdd.database.seeder

import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.database.entities.ItemThemes
import com.example.composablepdd.database.entities.ItemTicket
import com.example.composablepdd.database.seeder.resources.ArrayResourcesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeederDatabase @Inject constructor(
    private val database: MainDataBase,
    private val resArray: ArrayResourcesDataBase
) {

    suspend fun seedDatabase() {
        withContext(Dispatchers.IO) {
            if (database.getDao().getItemCountQuestions() == 0) {
                val questions = resArray.itemNumber.indices.map { i ->
                    ItemQuestions(
                        null,
                        itemNumber = resArray.itemNumber[i],
                        listNumber = resArray.listNumber[i],
                        marathonOrder = (1..800).toList().shuffled()[i],
                        name = resArray.listName[i],
                        text1 = resArray.listVar1[i],
                        text2 = resArray.listVar2[i],
                        text3 = resArray.listVar3[i],
                        text4 = resArray.listVar4[i],
                        text5 = resArray.listVar5[i],
                        textHelp = resArray.listTextHelp[i],
                        clickTrue = resArray.checkVariantTrue[i],
                        checkFlag = false,
                        image = resArray.listImage[i],
                        variantClick = null,
                        numberListThemes = resArray.numberListThemes[i],
                        numberListThemes2 = resArray.numberList2Themes[i]
                    )
                }
                database.getDao().insertItemQuestions(questions)
            }


            if (database.getDao().getItemCountTicket() == 0) {
                val tickets = resArray.listNumTicket.indices.map { i ->
                    ItemTicket(
                        numberList = (1..40).toList()[i],
                        errorQuality = 0,
                        allItemCounter = 20,
                        checkedItemCounter = 0,
                    )
                }
                database.getDao().insertItemTickets(tickets)
            }


            if (database.getDao().getItemCountThemes() == 0) {
                val themes = resArray.listThemeName.indices.map { i ->
                    ItemThemes(
                        name = resArray.listThemeName[i],
                        allItemCounter = resArray.listThemeCount[i],
                        progress = 0
                    )
                }
                database.getDao().insertItemThemes(themes)
            }
        }
    }
}