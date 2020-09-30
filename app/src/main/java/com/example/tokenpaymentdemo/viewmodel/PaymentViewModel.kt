package com.example.tokenpaymentdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tokenpaymentdemo.model.PaymentResponseModel
import com.example.tokenpaymentdemo.repository.PaymentRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel(private val paymentRepository: PaymentRepository) :
    BaseViewModel<PaymentResponseModel>() {

    val fetch = MutableLiveData<PaymentResponseModel>()
    val fetchLive: LiveData<PaymentResponseModel> = fetch

    init {
        fetchPaymentApi()
    }

    private fun fetchPaymentApi() {
        dataLoading.value = true
        paymentRepository.getPayment().enqueue(object : Callback<PaymentResponseModel> {
            override fun onResponse(
                call: Call<PaymentResponseModel>,
                response: Response<PaymentResponseModel>
            ) {
                if (!response.isSuccessful) {
                    empty.value = true
                    return
                }

                fetch.value = response.body()
                empty.value = false
            }

            override fun onFailure(call: Call<PaymentResponseModel>, t: Throwable) {

            }

        })
    }
}