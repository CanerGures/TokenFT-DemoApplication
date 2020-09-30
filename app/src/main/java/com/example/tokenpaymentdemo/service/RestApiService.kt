package com.example.tokenpaymentdemo.service


class RestApiService {
//    fun fetchPayQR(getQrBodyData: GetQrBodyModel, onResult: (GetQrResponseModel?) -> Unit) {
//        val retrofit = WebClient.buildService(ApiServiceGetQR::class.java)
//        retrofit.getQR(getQrBodyData).enqueue(
//            object : Callback<GetQrResponseModel> {
//                override fun onFailure(call: Call<GetQrResponseModel>, t: Throwable) {
//                    onResult(null)
//                }
//
//                override fun onResponse(
//                    call: Call<GetQrResponseModel>,
//                    response: Response<GetQrResponseModel>
//                ) {
//                    val getQR = response.body()
//                    onResult(getQR)
//                }
//            }
//        )
//    }
//}
//
//class RestApiServicePayment {
//    fun paymentService(
//        paymentBodyData: PaymentBodyModel,
//        onResult: (PaymentResponseModel?) -> Unit
//    ) {
//        val retrofit = WebClient.buildService(ApiServicePayment::class.java)
//        retrofit.getQR(paymentBodyData).enqueue(
//            object : Callback<PaymentResponseModel> {
//                override fun onFailure(call: Call<PaymentResponseModel>, t: Throwable) {
//                    onResult(null)
//                }
//
//                override fun onResponse(
//                    call: Call<PaymentResponseModel>,
//                    response: Response<PaymentResponseModel>
//                ) {
//                    val pay = response.body()
//                    onResult(pay)
//                }
//            }
//        )
//    }
}