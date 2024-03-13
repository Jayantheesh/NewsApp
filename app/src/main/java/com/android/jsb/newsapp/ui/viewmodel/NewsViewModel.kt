package com.android.jsb.newsapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.jsb.newsapp.database.NewsAPIResultsEntity
import com.android.jsb.newsapp.model.NewsAPIResponse
import com.android.jsb.newsapp.repository.NewsRepository
import com.android.jsb.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val topNewsResponse : MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()
    private val businessNewsResponse : MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()
    private val sportsNewsResponse : MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()
    var latestNews : LiveData<List<NewsAPIResultsEntity>> = MutableLiveData()

    init {
        getAllTopNews()
        //getBusinessNews()
        //getSportsNews()
        latestNews = repository.latestNews
    }

    private fun getAllTopNews() = viewModelScope.launch {
        topNewsResponse.postValue(Resource.Loading())
        val response = repository.getAllTopNews()
        println(response)
        topNewsResponse.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response : Response<NewsAPIResponse>) : Resource<NewsAPIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun getBusinessNews() = viewModelScope.launch {
        businessNewsResponse.postValue(Resource.Loading())
        val response = repository.getBusinessNews()
        businessNewsResponse.postValue(handleNewsResponse(response))
        println(response)
    }

    private fun getSportsNews() = viewModelScope.launch {
        sportsNewsResponse.postValue(Resource.Loading())
        val response = repository.getSportsNews()
        sportsNewsResponse.postValue(handleNewsResponse(response))
        println(response)
    }
}