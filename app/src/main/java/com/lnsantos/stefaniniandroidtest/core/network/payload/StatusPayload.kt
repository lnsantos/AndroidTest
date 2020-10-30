package com.lnsantos.stefaniniandroidtest.core.network.payload

import com.google.gson.annotations.SerializedName

data class StatusPayload(
    @SerializedName("success") var success:Boolean? = null,
    @SerializedName("status")  var status:Int? = null
)