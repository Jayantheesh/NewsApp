package com.android.jsb.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDBDataDao {
    //top news from news_results_table
    @Query("SELECT * FROM news_results_table WHERE newsCategory=\"top_news\"")
    fun getAllTopNews() : Flow<List<NewsAPIResultsEntity>>
    @Query("DELETE FROM news_results_table WHERE newsCategory=\"top_news\"")
    suspend fun clearAllTopNews()

    //business news from news_results_table
    @Query("SELECT * FROM news_results_table WHERE newsCategory=\"business\"")
    fun getAllBusinessNews() : Flow<List<NewsAPIResultsEntity>>
    @Query("DELETE FROM news_results_table WHERE newsCategory=\"business\"")
    suspend fun clearAllBusinessNews()

    //sports news from news_results_table
    @Query("SELECT * FROM news_results_table WHERE newsCategory=\"sports\"")
    fun getAllSportsNews() : Flow<List<NewsAPIResultsEntity>>
    @Query("DELETE FROM news_results_table WHERE newsCategory=\"sports\"")
    suspend fun clearAllSportsNews()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(results: List<NewsAPIResultsEntity>)

}