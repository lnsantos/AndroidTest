package com.lnsantos.stefaniniandroidtest.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lnsantos.stefaniniandroidtest.core.model.Image
import com.lnsantos.stefaniniandroidtest.core.repositories.interfaces.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel
    @ViewModelInject constructor(private val model: RemoteRepository<Int, List<Image>>) : ViewModel() {

    private var page = 1;
    private var dontResolveRequest = false

    private val _loading = MutableLiveData<Boolean>()
    val liveDataLoading : LiveData<Boolean> = _loading

    private val _images = MutableLiveData<List<Image>>()
    val liveDataImages : LiveData<List<Image>> = _images

    fun fetchImages(){

        if (dontResolveRequest){
            return
        }

        dontResolveRequest = true

        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val newList = model.fetch(page)
            val oldList = _images.value ?: arrayListOf()
            _images.postValue(arrayListOf(oldList, newList).flatten())
            _loading.postValue(false)

            page++

            dontResolveRequest = false
        }

    }

}