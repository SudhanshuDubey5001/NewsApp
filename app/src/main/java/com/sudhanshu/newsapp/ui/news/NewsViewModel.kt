package com.sudhanshu.newsapp.ui.news

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val newsID = savedStateHandle.get<Int>(Util.NEWS_ID)

    private val _news = MutableStateFlow<News?>(null)

    init {
        viewModelScope.launch {
            _news.filter {
                it?._id?.let { it1 -> Integer.parseInt(it1) } == newsID
            }.collectLatest {
                //got the specific news object

            }
        }
    }
}