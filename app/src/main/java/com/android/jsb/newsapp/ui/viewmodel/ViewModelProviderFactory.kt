package com.android.jsb.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.jsb.newsapp.repository.NewsRepository

class ViewModelProviderFactory (
    private val newsRepository: NewsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }

}