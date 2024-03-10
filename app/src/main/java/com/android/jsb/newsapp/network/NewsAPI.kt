package com.android.jsb.newsapp.network

import com.android.jsb.newsapp.model.NewsAPIResponse
import com.android.jsb.newsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines/")
    suspend fun getTopNews(
        @Query("apiKey")
        apiKey : String = Constants.API_KEY,
        @Query("country")
        country : String = Constants.USA
    ) : Response<NewsAPIResponse>

    @GET("v2/top-headlines/")
    suspend fun getBusinessNews(
        @Query("apiKey")
        apiKey : String = Constants.API_KEY,
        @Query("country")
        country : String = Constants.USA,
        @Query("category")
        category : String = Constants.BUSINESS
    ) : Response<NewsAPIResponse>

    @GET("v2/top-headlines/")
    suspend fun getSportsNews(
        @Query("apiKey")
        apiKey : String = Constants.API_KEY,
        @Query("country")
        country : String = Constants.USA,
        @Query("category")
        category : String = Constants.SPORTS
    ) : Response<NewsAPIResponse>
}
