package com.example.tokenpaymentdemo.model

import com.google.gson.annotations.SerializedName

data class GetQrResponseModel(

    @SerializedName("returnCode") val returnCode: Int,
    @SerializedName("returnDesc") val returnDesc: String,
    @SerializedName("QRdata") val qRdata: String
)