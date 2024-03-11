package com.android.jsb.newsapp.repository

import com.android.jsb.newsapp.database.NewsAPIResultsEntity
import com.android.jsb.newsapp.database.NewsAppDatabase
import com.android.jsb.newsapp.model.NewsAPIResponse
import com.android.jsb.newsapp.model.asDomainModel
import com.android.jsb.newsapp.network.RetrofitInstance
import com.android.jsb.newsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepository (private val database: NewsAppDatabase) {

    val nowPlaying: Flow<List<NewsAPIResultsEntity>> = database.dao.getAllTopNews()

    suspend fun getAllTopNews() : Response<NewsAPIResponse> {
        val response : Response<NewsAPIResponse> = RetrofitInstance.api.getTopNews()
        if (response.isSuccessful) {
            database.dao.insertNews(response.body()!!.asDomainModel(Constants.TOP_NEWS))
        }
        return response
    }

    suspend fun getBusinessNews() : Response<NewsAPIResponse> {
        val response : Response<NewsAPIResponse> = RetrofitInstance.api.getBusinessNews()
        if (response.isSuccessful) {
            database.dao.insertNews(response.body()!!.asDomainModel(Constants.BUSINESS))
        }
        return response
    }

    suspend fun getSportsNews() : Response<NewsAPIResponse> {
        val response : Response<NewsAPIResponse> = RetrofitInstance.api.getSportsNews()
        if (response.isSuccessful) {
            database.dao.insertNews(response.body()!!.asDomainModel(Constants.SPORTS))
        }
        return response
    }

}