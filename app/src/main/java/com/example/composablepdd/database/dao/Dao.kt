package com.example.composablepdd.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.composablepdd.database.entities.ItemQuestions
import com.example.composablepdd.database.entities.ItemThemes
import com.example.composablepdd.database.entities.ItemTicket
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT COUNT(*) FROM item_ticket")
    suspend fun getItemCountTicket(): Int

    @Query("SELECT * FROM item_ticket")
    fun getFullListTicket(): Flow<List<ItemTicket>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemTickets(tickets: List<ItemTicket>)

    @Update
    suspend fun updateItemTicket(item: ItemTicket)

    @Query("UPDATE item_ticket SET checkedItemCounter = :newValue, errorQuality = :newMistake WHERE id = :id")
    suspend fun updateValueByIdItemTicket(id: Int, newValue: Int, newMistake: Int)



    @Query("SELECT COUNT(*) FROM item_themes")
    suspend fun getItemCountThemes(): Int

    @Query("SELECT * FROM item_themes")
    fun getFullListThemes(): Flow<List<ItemThemes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemThemes(themes: List<ItemThemes>)

    @Update
    suspend fun updateItemThemes(item: ItemThemes)

    @Query("UPDATE item_themes SET progress = :newValue WHERE id = :id")
    suspend fun updateValueByIdItemThemes(id: Int, newValue: Int)



    @Query("SELECT COUNT(*) FROM item_questions")
    suspend fun getItemCountQuestions(): Int

    @Query("SELECT * FROM item_questions")
    fun getFullListQuestions(): Flow<List<ItemQuestions>>

    @Query("SELECT * FROM item_questions ORDER BY marathonOrder ASC")
    fun getFullListQuestionsOrder(): Flow<List<ItemQuestions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemQuestions(questions: List<ItemQuestions>)

    @Update
    suspend fun updateItemQuestions(item: ItemQuestions)

    @Update
    suspend fun updateListQuestions(list: List<ItemQuestions>)

    @Transaction
    suspend fun updateValuesByIds(
        ids: List<Int>,
        newValues: List<Int>
    ) {
        require(ids.size == newValues.size)
        val sqlBuilder = StringBuilder("UPDATE item_questions SET marathonOrder = CASE id ")
        for (i in ids.indices) {
            sqlBuilder.append("WHEN ${ids[i]} THEN ${newValues[i]} ")
        }
        sqlBuilder.append("END, variantClickMarathon = 0 WHERE id IN (${ids.joinToString(", ")})")
        val query = SimpleSQLiteQuery(sqlBuilder.toString())
        executeUpdate(query)
    }
    @RawQuery
    suspend fun executeUpdate(query: SimpleSQLiteQuery): Int

    @Query("SELECT COUNT(*) FROM item_questions WHERE variantClickMarathon != 0 AND variantClickMarathon IS NOT NULL")
    fun getItemCountQuestionsMarathonCheck(): Flow<Int>

}