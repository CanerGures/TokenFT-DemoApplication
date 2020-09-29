package com.example.tokenpaymentdemo.service

import com.example.tokenpaymentdemo.model.GetQrBodyModel
import com.example.tokenpaymentdemo.model.GetQrResponseModel
import com.example.tokenpaymentdemo.model.PaymentBodyModel
import com.example.tokenpaymentdemo.model.PaymentResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceGetQR {
    @Headers(
        "Accept:application/json",
        "Content-type:application/json",
        "x-ibm-client-id:d56a0277-2ee3-4ae5-97c8-467abeda984d",
        "x-ibm-client-secret:U1yT3qD2jW6oO4uH8gB8bN1xW0xH3aL7jN2lT7dP5aL5rQ1vK4"
    )
    @POST("api/get_qr_sale")
    fun getQR(@Body getQrBody: GetQrBodyModel): Call<GetQrResponseModel>
}

interface ApiServicePayment {
    @Headers(
        "Accept:application/json",
        "Content-type:application/json",
        "x-ibm-client-id:d56a0277-2ee3-4ae5-97c8-467abeda984d",
        "x-ibm-client-secret:U1yT3qD2jW6oO4uH8gB8bN1xW0xH3aL7jN2lT7dP5aL5rQ1vK4"
    )
    @POST("api/payment")
    fun getQR(@Body getQrBody: PaymentBodyModel): Call<PaymentResponseModel>
}