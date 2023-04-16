package com.sudhanshu.newsapp.ui.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.NewsBase
import com.sudhanshu.newsapp.util.Routes
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val newsApi: NewsApi
) : ViewModel() {

    private var _newsList = MutableStateFlow(listOf<News>())
    val newsList = _newsList.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
//        getLatestNews()
    }

    fun onNewsFeedEvent(event: NewsFeedEvents) {
        when (event) {
            is NewsFeedEvents.OnNewsClick -> {
                sendUiEvents(UiEvent.navigate(Routes.NEWS + "?newsID=" + event.news._id))
            }
            NewsFeedEvents.OnRefreshNews -> {
                sendUiEvents(UiEvent.refreshNews)
            }
            NewsFeedEvents.OnSearchNewsClick -> {
                sendUiEvents(UiEvent.navigate(Routes.NEWS_SEARCH))
            }
        }
    }

    fun getLatestNews() {
        Util.log("Viewmodel started.....")
        //start on a fresh list

        viewModelScope.launch {
            val response = try {
                newsApi.getDeafaultNews()
            } catch (e: IOException) {
                Util.log(e.toString())
                return@launch
            } catch (e: HttpException) {
                Util.log(e.toString())
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                val newsBase = response.body()!!
                _newsList.emit(newsBase.articles)
            } else {
                Util.log("Error occured::" + response.body())
                sendUiEvents(
                    UiEvent.showSnackbar(
                        content = "Check internet connection",
                        action = "Retry"
                    )
                )
            }
        }
    }

    private fun sendUiEvents(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}