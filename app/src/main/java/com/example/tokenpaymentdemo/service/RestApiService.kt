package com.example.tokenpaymentdemo.service


import com.example.tokenpaymentdemo.model.GetQrBodyModel
import com.example.tokenpaymentdemo.model.GetQrResponseModel
import com.example.tokenpaymentdemo.model.PaymentBodyModel
import com.example.tokenpaymentdemo.model.PaymentResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    fun fetchPayQR(getQrBodyData: GetQrBodyModel, onResult: (GetQrResponseModel?) -> Unit) {
        val retrofit = WebClient.buildService(ApiServiceGetQR::class.java)
        retrofit.getQR(getQrBodyData).enqueue(
            object : Callback<GetQrResponseModel> {
                override fun onFailure(call: Call<GetQrResponseModel>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<GetQrResponseModel>,
                    response: Response<GetQrResponseModel>
                ) {
                    val getQR = response.body()
                    onResult(getQR)
                }
            }
        )
    }
}

class RestApiServicePayment {
    fun paymentService(
        paymentBodyData: PaymentBodyModel,
        onResult: (PaymentResponseModel?) -> Unit
    ) {
        val retrofit = WebClient.buildService(ApiServicePayment::class.java)
        retrofit.getQR(paymentBodyData).enqueue(
            object : Callback<PaymentResponseModel> {
                override fun onFailure(call: Call<PaymentResponseModel>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<PaymentResponseModel>,
                    response: Response<PaymentResponseModel>
                ) {
                    val pay = response.body()
                    onResult(pay)
                }
            }
        )
    }
}