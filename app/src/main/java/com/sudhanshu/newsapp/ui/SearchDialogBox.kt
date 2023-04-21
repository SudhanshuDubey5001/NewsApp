package com.sudhanshu.newsapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedEvents
import com.sudhanshu.newsapp.ui.newsfeed.NewsFeedViewModel
import com.sudhanshu.newsapp.ui.newsfeed.searchDialogBoxState

@Composable
fun SearchDialogBox(
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    MaterialTheme {
        if (searchDialogBoxState.value) {
            AlertDialog(onDismissRequest = { searchDialogBoxState.value = false },
                title = {
                    Text("Search")
                },
                text = {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        shadowElevation = 5.dp
                    ) {
                        val focus = LocalFocusManager.current
                        Row() {
                            Surface(modifier = Modifier.weight(1f)) {
                                OutlinedTextField(
                                    value = textState.value,
                                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                    onValueChange = {
                                        textState.value = it
                                        Log.d("mylog", "Edittext value: " + textState.value)
                                    },
                                    placeholder = { Text("Search topics and articles") },
                                    keyboardActions = KeyboardActions(onDone = { focus.clearFocus() },
                                        onSearch = {
                                            viewModel.onNewsFeedEvent(NewsFeedEvents.OnSearchNewsClick(textState.value.text))
                                            focus.clearFocus()
                                            Log.d("mylog", "Query: " + textState.value.text)
                                            textState.value = TextFieldValue("")
                                        })
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.onNewsFeedEvent(NewsFeedEvents.OnSearchNewsClick(textState.value.text))
                        searchDialogBoxState.value = false
                    }) {
                        Text(text = "Search")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        searchDialogBoxState.value = false
                    }) {
                        Text("Cancel")
                    }
                })
        }
    }
}

@Preview
@Composable
fun preview(){
    SearchDialogBox()
}