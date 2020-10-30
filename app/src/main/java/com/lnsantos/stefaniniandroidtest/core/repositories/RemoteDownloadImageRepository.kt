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
                validateDataImage(image, gallery.title)
            } ?: arrayListOf()
        } ?: arrayListOf()

        return imagesListOfList.flatten().filterNotNull()
    }

    private fun validateDataImage(imagePayload: ImagePayload, galleryName: String?): Image? {
        try {

            if (imagePayload.height == 0 ||
                imagePayload.width == 0 ||
                imagePayload.link == null
            ) {
                return null
            }

            return Image().toConvertPayload(imagePayload, galleryName)
        } catch (e: Exception) {
            Timber.e(e)
            return null
        }
    }



}
