package com.lnsantos.stefaniniandroidtest.core.network.payload

import com.google.gson.annotations.SerializedName

data class GalleryPayload (
    @SerializedName("id")     var id:String? = null,
    @SerializedName("title")  var title:String? = null,
    @SerializedName("images") var images:List<ImagePayload>? = null,
    @SerializedName("tags")   var tags:List<TagPayload>? = null
)