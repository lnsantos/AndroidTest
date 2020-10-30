package com.lnsantos.stefaniniandroidtest.core.network.payload

import com.google.gson.annotations.SerializedName

data class ImagePayload(
    @SerializedName("id")          var id:String? = null,
    @SerializedName("title")       var title:String? = null,
    @SerializedName("description") var description:String? = null,
    @SerializedName("type")        var type:String? = null,
    @SerializedName("animated")    var animated:Boolean? = false,
    @SerializedName("width")       var width:Int? = 0,
    @SerializedName("height")      var height:Int? = 0,
    @SerializedName("size")        var size:Long? = 0L,
    @SerializedName("link")        var link:String?= null,
    @SerializedName("mp4")         var mediaPlayView:String?= null,
    @SerializedName("gifv")        var gifVideo:String? = null

)