package com.lnsantos.stefaniniandroidtest.core.network.payload

import com.google.gson.annotations.SerializedName

data class TagPayload (
    @SerializedName("name") var name:String? = "",
    @SerializedName("display_name") var displayName:String? = ""
)