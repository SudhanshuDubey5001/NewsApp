package com.sudhanshu.newsapp.ui.news

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.newsTest
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedViewModel
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val newsJSON = savedStateHandle.get<String>(Util.NEWS_ID)

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val newsFlowObj = newsJSON?.let { Util.fromJson(it) }

    fun OnNewsEvent(event: NewsEvent) {
        when (event) {
            NewsEvent.popBackStack -> {
                sendUiEvents(UiEvent.popBackStack)
            }
        }
    }

    private fun sendUiEvents(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}