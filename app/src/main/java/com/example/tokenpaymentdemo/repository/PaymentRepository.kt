package com.example.tokenpaymentdemo.repository

import com.example.tokenpaymentdemo.model.PaymentBodyModel
import com.example.tokenpaymentdemo.service.ApiService

class PaymentRepository(
    private val service: ApiService,
    private val getPaymentBody: PaymentBodyModel
) {

    fun getPayment() = service.getPayment(getPaymentBody)
}