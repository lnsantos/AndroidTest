package com.lnsantos.stefaniniandroidtest.core.network.payload

import com.google.gson.annotations.SerializedName

class DataSourcePayload (
    @SerializedName("data") var galleries: List<GalleryPayload>?= null,
    @SerializedName("success") var success:Boolean? = null,
    @SerializedName("status")  var status:Int? = null
)