package com.lnsantos.stefaniniandroidtest.core.model

import com.lnsantos.stefaniniandroidtest.core.network.payload.ImagePayload

data class Image(
    val id:String? = "",
    val title:String? = null,
    val description:String? = null,
    val type:String? = null,
    val animated:Boolean? = false,
    val width:Int? = 0,
    val height:Int? = 0,
    val size:Long? = 0L,
    val urlImage:String?= null,
    val urlMp4:String?= null,
    val urlGif:String? = null,
    val galleryName:String? = "",
    val tags: List<Tag> = arrayListOf()
){

    fun toConvertPayload(payload: ImagePayload,galleryName: String?) : Image{
        return Image(payload.id,
                     payload.title,
                     payload.description,
                     payload.type,
                     payload.animated,
                     payload.width,
                     payload.height,
                     payload.size,
                     payload.link,
                     payload.mediaPlayView,
                     payload.gifVideo,
                     galleryName,
                     arrayListOf())
    }

}