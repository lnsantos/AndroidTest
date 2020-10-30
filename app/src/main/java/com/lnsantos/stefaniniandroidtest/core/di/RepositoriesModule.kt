package com.lnsantos.stefaniniandroidtest.core.di

import com.lnsantos.stefaniniandroidtest.core.model.Image
import com.lnsantos.stefaniniandroidtest.core.network.ImgurService
import com.lnsantos.stefaniniandroidtest.core.repositories.RemoteDownloadImageRepository
import com.lnsantos.stefaniniandroidtest.core.repositories.interfaces.RemoteRepository
import com.lnsantos.stefaniniandroidtest.main.repositories.MainRepositoryImpl
import com.lnsantos.stefaniniandroidtest.main.repositories.interfaces.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RepositoriesModule {

    @Provides
    fun bindRemoteDownloadImage(api: ImgurService) : RemoteRepository<Int, List<Image>>{
        return RemoteDownloadImageRepository(api)
    }

   @Provides
    fun bindMainRepositoryImp(mainRepositoryImp: RemoteRepository<Int, List<Image>>) : MainRepository{
        return MainRepositoryImpl(mainRepositoryImp)
    }

}


