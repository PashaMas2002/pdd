package com.example.composablepdd.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.composablepdd.database.MainDataBase
import com.example.composablepdd.database.seeder.SeederDatabase
import com.example.composablepdd.database.seeder.resources.ArrayResourcesDataBase
import com.example.composablepdd.resources.book.Book
import com.example.composablepdd.resources.link.ResLinks
import com.example.composablepdd.database.getered.GetStatistics
import com.example.composablepdd.resources.region.ResourcesRegion
import com.example.composablepdd.resources.testBook.TestBook
import com.example.composablepdd.ui.myTheme.AppTheme
import com.example.composablepdd.database.preferences.SaveValueSettings
import com.example.composablepdd.model.DataBaseForTest
import com.example.composablepdd.screens.test.func.ButtonClick
import com.example.composablepdd.screens.test.func.ColorElementsTest
import com.example.composablepdd.database.preferences.SaveTabPosition
import com.example.composablepdd.database.getered.GetTableThemes
import com.example.composablepdd.database.getered.GetTableTicket
import com.example.composablepdd.database.preferences.SaveReminder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDataBase {
        return Room.databaseBuilder(
            context,
            MainDataBase::class.java,
            "database.db"
        ).build()
    }

    @Provides
    fun provideArrayResources(application: Application): ArrayResourcesDataBase {
        return ArrayResourcesDataBase(application)
    }

    @Provides
    fun provideSeederDatabase(
        database: MainDataBase,
        resArray: ArrayResourcesDataBase
    ): SeederDatabase {
        return SeederDatabase(database, resArray)
    }

    @Provides
    @Singleton
    fun providerGetStatistics(
        database: MainDataBase,
    ): GetStatistics {
        return GetStatistics(database)
    }

    @Provides
    @Singleton
    fun providerGetTableTicket(
        database: MainDataBase
    ): GetTableTicket {
        return GetTableTicket(database)
    }

    @Provides
    @Singleton
    fun provideGetTableThemes(
        database: MainDataBase
    ): GetTableThemes {
        return GetTableThemes(database)
    }

    @Provides
    @Singleton
    fun provideStartTest(
        database: MainDataBase
    ): DataBaseForTest {
        return DataBaseForTest(database)
    }

    @Provides
    fun provideRegionResources(): ResourcesRegion {
        return ResourcesRegion()
    }

    @Provides
    fun provideButtonClick(): ButtonClick {
        return ButtonClick()
    }

    @Provides
    fun provideColorElementsTest(): ColorElementsTest {
        return ColorElementsTest()
    }

    @Provides
    @Singleton
    fun provideColorSaveTabPosition(application: Application): SaveTabPosition {
        return SaveTabPosition(application)
    }

    @Provides
    @Singleton
    fun provideSaveValueSettings(application: Application): SaveValueSettings {
        return SaveValueSettings(application)
    }

    @Provides
    @Singleton
    fun provideSaveReminder(application: Application): SaveReminder {
        return SaveReminder(application)
    }

    @Provides
    @Singleton
    fun provideAppTheme(saves: SaveValueSettings): AppTheme {
        return AppTheme(saves)
    }

    @Provides
    fun providerResLinks(): ResLinks {
        return ResLinks()
    }

    @Provides
    fun providerTestBook(): TestBook {
        return TestBook()
    }
    @Provides
    @Singleton
    fun providerNameBook(): Book {
        return Book()
    }
}