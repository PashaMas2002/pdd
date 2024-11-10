package com.example.composablepdd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composablepdd.database.dao.Dao
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.database.entities.ItemThemes
import com.example.composablepdd.database.entities.ItemTicket

@Database(entities = [ItemThemes::class, ItemTicket::class, ItemQuestions::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: MainDataBase? = null

        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "database.db"
                ).build()
                instance
            }
        }
    }
}