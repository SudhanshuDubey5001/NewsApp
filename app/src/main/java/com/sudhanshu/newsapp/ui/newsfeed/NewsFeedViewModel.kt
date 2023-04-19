package com.sudhanshu.newsapp.ui.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhanshu.newsapp.data.ApiCallEvents
import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.data.repository.News
import com.sudhanshu.newsapp.data.repository.NewsBase
import com.sudhanshu.newsapp.util.Routes
import com.sudhanshu.newsapp.util.UiEvent
import com.sudhanshu.newsapp.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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

    var safety: Boolean = true  //init is calling twice. No idea why????

    init {
        OnApiCall(ApiCallEvents.getLatestNews)
    }

    fun onNewsFeedEvent(event: NewsFeedEvents) {
        when (event) {
            is NewsFeedEvents.OnNewsClick -> {
                Util.log("JSON formed: " + Util.toJson(event.news))
                sendUiEvents(UiEvent.navigate(Routes.NEWS + "?newsJSON=" + Util.toJson(event.news)))
            }
            NewsFeedEvents.OnRefreshNews -> {

            }
            NewsFeedEvents.OnSearchNewsClick -> {
                sendUiEvents(UiEvent.navigate(Routes.NEWS_SEARCH))
            }
            is NewsFeedEvents.OnNavigationDrawerItemClicked -> {
                safety = true
                OnApiCall(ApiCallEvents.getTopicNews(event.topic))
            }
        }
    }

    fun OnApiCall(event: ApiCallEvents) {
        when (event) {
            ApiCallEvents.getLatestNews -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getNews(Util.LATEST_NEWS,"")
                }
            }
            is ApiCallEvents.getTopicNews -> {
                viewModelScope.launch (Dispatchers.IO){
                    getNews(Util.TOPIC_NEWS, event.topic)
                }
            }
        }
    }

    suspend fun getNews(id: String, args: String) {
        if (safety) {
            safety = false
            Util.log("Fetching latest news.....")
            val response = try {
                if (id.equals(Util.LATEST_NEWS)) {
                    newsApi.getDeafaultNews()
                } else {
                    newsApi.getTopicNews("en", args!!)
                }
            } catch (e: IOException) {
                Util.log(e.toString())
                return
            } catch (e: HttpException) {
                Util.log(e.toString())
                sendUiEvents(
                    UiEvent.showSnackbar(
                        content = "Check internet connection",
                        action = "Retry"
                    )
                )
                return
            }

            if (response.isSuccessful && response.body() != null) {
                val newsBase = response.body()!!
                Util.log("TOpic news = " + response.body().toString())
                //start on fresh list
//                _newsList = MutableStateFlow(emptyList())
                //remove elements which doesn't have following attributes
                _newsList.value = _newsList.value.filter {
                    it.title != null &&
                            it.excerpt != null &&
                            it.summary != null &&
                            it.media != null
                }
                _newsList.emit(newsBase.articles)
            } else {
                Util.log("Error occured::" + response.message().toString())
                Util.log("Error raw::" + response.raw().toString())
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