package com.lnsantos.stefaniniandroidtest.main.repositories.interfaces

import com.lnsantos.stefaniniandroidtest.core.model.Image

interface MainRepository {

    suspend fun fetchImages(page: Int) : List<Image>

}