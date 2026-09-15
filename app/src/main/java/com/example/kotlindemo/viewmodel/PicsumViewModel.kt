package com.example.kotlindemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo.data.model.PicsumImageDto
import com.example.kotlindemo.data.repository.PicsumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PicsumViewModel @Inject constructor(private val repository: PicsumRepository): ViewModel() {
    private val _posts = MutableStateFlow<List<PicsumImageDto>>(emptyList())
    val posts: StateFlow<List<PicsumImageDto>> = _posts.asStateFlow()
    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading:StateFlow<Boolean> = _isLoading;

    //If you want the API to automatically load when the screen opens, put it in the ViewModel's init
    init {
        _isLoading.value = true;
        fetchData(1, 20)
    }


    fun fetchData(page: Int, limit: Int){
        viewModelScope.launch {
          val response =  repository.getPosts(page, limit)
            _posts.value = response
            _isLoading.value = false;
        }
    }
}