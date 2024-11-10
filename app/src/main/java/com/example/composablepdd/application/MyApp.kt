package com.example.composablepdd.application

import android.app.Application
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.seeder.SeederDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApp: Application() {
    val database by lazy { MainDataBase.getDataBase(this)}

    @Inject
    lateinit var seederDatabase: SeederDatabase

    override fun onCreate() {
        super.onCreate()
        seedDatabase()
    }
    private fun seedDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            seederDatabase.seedDatabase()
        }
    }
}
