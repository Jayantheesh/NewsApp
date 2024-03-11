package com.android.jsb.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.jsb.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    init {
        getAllTopNews()
        getBusinessNews()
        getSportsNews()
    }

    private fun getAllTopNews() = viewModelScope.launch {
        val response = repository.getAllTopNews()
        println(response)
    }

    private fun getBusinessNews() = viewModelScope.launch {
        val response = repository.getBusinessNews()
        println(response)
    }

    private fun getSportsNews() = viewModelScope.launch {
        val response = repository.getSportsNews()
        println(response)
    }
}