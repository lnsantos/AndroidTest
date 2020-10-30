package com.lnsantos.stefaniniandroidtest.core.repositories

import com.lnsantos.stefaniniandroidtest.core.model.Image
import com.lnsantos.stefaniniandroidtest.core.network.ImgurService
import com.lnsantos.stefaniniandroidtest.core.network.payload.DataSourcePayload
import com.lnsantos.stefaniniandroidtest.core.network.payload.ImagePayload
import com.lnsantos.stefaniniandroidtest.core.repositories.interfaces.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.Exception

class RemoteDownloadImageRepository
@Inject constructor(private val api: ImgurService) : RemoteRepository<Int, List<Image>> {

    override suspend fun fetch(page:Int): List<Image> = withContext(Dispatchers.IO) {
        val response = api.fetchGalleryHot(page)
        return@withContext processPayload(response)
    }


    private fun processPayload(payload: DataSourcePayload): List<Image> {

        val imagesListOfList: List<List<Image?>> = payload.galleries?.map { gallery ->
            gallery.images?.map { image ->

                if (image.height != 0 || image.width != 0 || image.link != null) {
                    Image().toConvertPayload(image, gallery.title )
                }else null

            } ?: arrayListOf()
        } ?: arrayListOf()

        return imagesListOfList.flatten().filterNotNull()
    }

}
