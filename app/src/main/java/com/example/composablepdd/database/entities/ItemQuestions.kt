package com.example.composablepdd.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_questions")
data class ItemQuestions(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,

    @ColumnInfo(name = "itemNumber")
    val itemNumber : Int,

    @ColumnInfo(name = "listNumber")
    val listNumber : Int,

    @ColumnInfo(name = "marathonOrder")
    val marathonOrder : Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "text1")
    val text1 : String? = null,

    @ColumnInfo(name = "text2")
    val text2 : String? = null,

    @ColumnInfo(name = "text3")
    val text3 : String? = null,

    @ColumnInfo(name = "text4")
    val text4 : String? = null,

    @ColumnInfo(name = "text5")
    val text5 : String? = null,

    @ColumnInfo(name = "textHelp")
    val textHelp : String? = null,

    @ColumnInfo(name = "clickTrue")
    val clickTrue : Int,

    @ColumnInfo(name = "checkFlag")
    var checkFlag : Boolean = false,

    @ColumnInfo(name = "image")
    val image : String? = null,

    @ColumnInfo(name = "variantClick")
    var variantClick : Int? = null,

    @ColumnInfo(name = "variantClickMarathon")
    val variantClickMarathon: Int? = null,

    @ColumnInfo(name = "numberListThemes")
    val numberListThemes : Int? = null,

    @ColumnInfo(name = "numberListThemes2")
    val numberListThemes2 : Int? = null,
)
