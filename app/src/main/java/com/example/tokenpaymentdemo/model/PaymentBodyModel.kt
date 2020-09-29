package com.example.tokenpaymentdemo.model

import com.google.gson.annotations.SerializedName

data class PaymentBodyModel(

    @SerializedName("returnCode") val returnCode: Int,
    @SerializedName("returnDesc") val returnDesc: String,
    @SerializedName("receiptMsgCustomer") val receiptMsgCustomer: String,
    @SerializedName("receiptMsgMerchant") val receiptMsgMerchant: String,
    @SerializedName("paymentInfoList") val paymentInfoList: List<PaymentInfoList>,
    @SerializedName("QRdata") val qRdata: String
)

data class PaymentActionList(

    @SerializedName("paymentType") val paymentType: Int,
    @SerializedName("amount") val amount: Int,
    @SerializedName("currencyID") val currencyID: Int,
    @SerializedName("vatRate") val vatRate: Int
)

data class PaymentInfoList(

    @SerializedName("paymentProcessorID") val paymentProcessorID: Int,
    @SerializedName("paymentActionList") val paymentActionList: List<PaymentActionList>
)