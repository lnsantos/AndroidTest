package com.lnsantos.stefaniniandroidtest.core.network

import com.lnsantos.stefaniniandroidtest.core.network.constantes.Headers.HEADER_AUTHORIZATION
import com.lnsantos.stefaniniandroidtest.core.network.constantes.Routers
import com.lnsantos.stefaniniandroidtest.core.network.payload.DataSourcePayload
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurService {

    @GET(Routers.API_ROUTER_DOWNLOAD_GALLERY_HOT)
    @Headers(HEADER_AUTHORIZATION)
    suspend fun fetchGalleryHot(@Path("page") page:Int) : DataSourcePayload

    @GET(Routers.API_ROUTER_DOWNLOAD_GALLERY_CATS)
    @Headers(HEADER_AUTHORIZATION)
    suspend fun fetchGalleryCats( @Path("page") page:Int, @Query("q") query: String,) : DataSourcePayload

}