package com.example.composablepdd.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_ticket")
data class ItemTicket(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "numberList")
    val numberList: Int? = null,

    @ColumnInfo(name = "errorQuality")
    val errorQuality: Int? = null,

    @ColumnInfo(name = "allItemCounter")
    val allItemCounter: Int? = null,

    @ColumnInfo(name = "checkedItemCounter")
    val checkedItemCounter: Int? = null,
)
