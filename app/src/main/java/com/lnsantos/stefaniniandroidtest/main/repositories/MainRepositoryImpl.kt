package com.lnsantos.stefaniniandroidtest.main.repositories

import com.lnsantos.stefaniniandroidtest.core.model.Image
import com.lnsantos.stefaniniandroidtest.core.repositories.interfaces.RemoteRepository
import com.lnsantos.stefaniniandroidtest.main.repositories.interfaces.MainRepository
import javax.inject.Inject

class MainRepositoryImpl
     @Inject constructor(private val remoteImageRepository: RemoteRepository<Int, List<Image>>) : MainRepository{

    override suspend fun fetchImages(page: Int): List<Image> = remoteImageRepository.fetch(page)

    companion object{
        val LOG_TAG = this::class.java.simpleName
    }
}