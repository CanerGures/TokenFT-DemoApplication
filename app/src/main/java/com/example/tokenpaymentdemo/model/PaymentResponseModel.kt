package com.example.tokenpaymentdemo.model

import com.google.gson.annotations.SerializedName

data class PaymentResponseModel(

    @SerializedName("applicationID") val applicationID: String,
    @SerializedName("sessionID") val sessionID: Int,
    @SerializedName("posID") val posID: String,
    @SerializedName("returnCode") val returnCode: Int,
    @SerializedName("returnDesc") val returnDesc: String
)