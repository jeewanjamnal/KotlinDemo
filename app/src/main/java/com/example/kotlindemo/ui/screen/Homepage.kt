package com.example.kotlindemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlindemo.viewmodel.PicsumViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun Homepage(modifier: Modifier) {
    val viewModel : PicsumViewModel = hiltViewModel()
    val photos by viewModel.posts.collectAsStateWithLifecycle();
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle();
    Column(modifier = modifier.fillMaxSize().safeDrawingPadding()) {
        if(isLoading){
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(photos){ photo ->
                Text(text = photo.author)
            }

        }
    }
}