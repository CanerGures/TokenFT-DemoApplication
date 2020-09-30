package com.example.tokenpaymentdemo.repository

import com.example.tokenpaymentdemo.model.GetQrBodyModel
import com.example.tokenpaymentdemo.service.ApiService


class FetchApiQrRepository(private val service: ApiService, private val getQrBody: GetQrBodyModel) {

    fun getQr() = service.getQR(getQrBody)
}