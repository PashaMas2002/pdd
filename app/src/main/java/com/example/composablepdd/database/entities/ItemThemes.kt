package com.example.composablepdd.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_themes")
data class ItemThemes(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "allItemCount")
    val allItemCounter : String,

    @ColumnInfo(name = "progress")
    val progress : Int?,
)
