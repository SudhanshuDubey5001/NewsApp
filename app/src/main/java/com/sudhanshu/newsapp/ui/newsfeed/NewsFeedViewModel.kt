package com.sudhanshu.newsapp.ui.newsfeed

import androidx.compose.runtime.*
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
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

//recomposition is not happening when i put these inside viewmodel
private var _newsList = MutableStateFlow(listOf<News>())
val newsList = _newsList.asStateFlow()

var progressLoader = mutableStateOf(false)
    private set

var searchDialogBoxState = mutableStateOf(false)
    private set

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val newsApi: NewsApi
) : ViewModel() {

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
            is NewsFeedEvents.OnSearchNewsClick -> {
                OnApiCall(ApiCallEvents.getQueryNews(event.query))
            }
            is NewsFeedEvents.OnNavigationDrawerItemClicked -> {
                safety = true
                //todo: change the "tech" here later---------->
                OnApiCall(ApiCallEvents.getTopicNews(event.topic))
            }
        }
    }

    fun OnApiCall(event: ApiCallEvents) {
        progressLoader.value = true
        when (event) {
            ApiCallEvents.getLatestNews -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getNews(Util.LATEST_NEWS, "")
                }
            }
            is ApiCallEvents.getTopicNews -> {
                safety = true
                viewModelScope.launch(Dispatchers.IO) {
                    getNews(Util.TOPIC_NEWS, event.topic)
                }
            }
            is ApiCallEvents.getQueryNews -> {
                safety = true
                viewModelScope.launch(Dispatchers.IO) {
                    getNews(Util.SEARCH_NEWS, event.query)
                }
            }
        }
    }

    suspend fun getNews(id: String, args: String) {
        if (safety) {
            safety = false
            try {
                var response: Response<NewsBase>? = null
                when (id) {
                    Util.LATEST_NEWS -> {
                        Util.log("Fetching default news.....")
                        response = newsApi.getDeafaultNews()
//                    newsApi._getdefaultNews()
                    }
                    Util.TOPIC_NEWS -> {
                        Util.log("Fetching topic news.....")
                        response = newsApi.getTopicNews(args, "en")
//                    newsApi._getTopicNews()
                    }
                    Util.SEARCH_NEWS -> {
                        Util.log("Fetching search news.....")
                        response = newsApi.performSearchQuery(args,"en")
                    }
                    else -> {}
                }

                if (response != null) {
                    if (response.isSuccessful && response.body() != null) {
                        progressLoader.value = false
                        val newsBase = response.body()!!
            //                Util.log("Response = " + response.body().toString())

                        //remove elements which doesn't have following attributes
                        newsBase.articles = newsBase.articles.filter {
                            it.title != null &&
                                    it.excerpt != null &&
                                    it.summary != null &&
                                    it.media != null
                        }
                        Util.log("Emitting values now....")
                        _newsList.emit(newsBase.articles)

                    } else {
                        Util.log("Error raw::" + response.raw().toString())
                        sendUiEvents(
                            UiEvent.showSnackbar(
                                content = "Check internet connection",
                                action = "Retry"
                            )
                        )
                        progressLoader.value = false
                    }
                }

            } catch (e: IOException) {
                Util.log("IO exception:::" + e.toString())
                progressLoader.value = false
                return
            } catch (e: HttpException) {
                Util.log("HTTP exception" + e.toString())
                sendUiEvents(
                    UiEvent.showSnackbar(
                        content = "Check internet connection",
                        action = "Retry"
                    )
                )
                progressLoader.value = false
                return
            }
        }
    }

    private fun sendUiEvents(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}