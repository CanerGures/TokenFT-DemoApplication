package com.example.tokenpaymentdemo.model

import com.google.gson.annotations.SerializedName

data class GetQrBodyModel(
    @SerializedName("totalReceiptAmount") val totalReceiptAmount: Int
)