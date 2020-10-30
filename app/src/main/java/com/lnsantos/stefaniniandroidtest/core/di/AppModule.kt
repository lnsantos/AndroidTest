package com.lnsantos.stefaniniandroidtest.core.di

import com.lnsantos.stefaniniandroidtest.BuildConfig
import com.lnsantos.stefaniniandroidtest.core.network.ImgurService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .baseUrl(BuildConfig.BASE_URL)
                                     .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : ImgurService = retrofit.create(ImgurService::class.java)
}