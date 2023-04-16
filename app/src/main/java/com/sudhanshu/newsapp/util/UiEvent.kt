package com.sudhanshu.newsapp.util

sealed class UiEvent {

    //pop back stack
    object popBackStack: UiEvent()

    //navigate
    data class navigate(val route: String) : UiEvent()

    //snackbar show
    data class showSnackbar(
        val content: String,
        val action: String? = null
    ) : UiEvent()

    //refresh screen to get latest news
    object refreshNews: UiEvent()

    //navigation drawer
    object navigationDrawer: UiEvent()
}