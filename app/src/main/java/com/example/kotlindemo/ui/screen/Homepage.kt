package com.example.kotlindemo.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlindemo.viewmodel.PicsumViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage

@Composable
fun Homepage(modifier: Modifier) {
    val viewModel : PicsumViewModel = hiltViewModel()
    val photos by viewModel.posts.collectAsStateWithLifecycle();
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle();
    Column(
        modifier = modifier.fillMaxSize().safeDrawingPadding()
    ) {
        if(isLoading){
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(photos) { photo ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment= Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = photo.downloadUrl,
                            contentDescription = photo.author,
                            modifier = Modifier.fillMaxSize().height(150.dp),
                            contentScale = ContentScale.Crop,
                            onError = {
                                Log.e("IMAGE_ERROR", "Failed: ${photo.downloadUrl}", it.result.throwable)
                            }
                        )
                        Spacer(modifier= Modifier.height(8.dp))
                        Text(
                            text = photo.author,
                            modifier = Modifier.padding(16.dp),
                            color = Color.Black
                        )
                    }
                }
            }

        }
    }
}