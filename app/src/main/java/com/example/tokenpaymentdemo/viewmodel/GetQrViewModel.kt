package com.example.tokenpaymentdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tokenpaymentdemo.model.GetQrResponseModel
import com.example.tokenpaymentdemo.repository.FetchApiQrRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetQrViewModel(private val fetchApiRepository: FetchApiQrRepository) :
    BaseViewModel<GetQrResponseModel>() {

    val fetch = MutableLiveData<GetQrResponseModel>()
    val fetchLive: LiveData<GetQrResponseModel> = fetch

    init {
        fetchQrApi()
    }

    private fun fetchQrApi() {
        dataLoading.value = true
        fetchApiRepository.getQr().enqueue(object : Callback<GetQrResponseModel> {
            override fun onResponse(
                call: Call<GetQrResponseModel>,
                response: Response<GetQrResponseModel>
            ) {
                if (!response.isSuccessful) {
                    empty.value = true
                    return
                }

                fetch.value = response.body()
                empty.value = false
            }

            override fun onFailure(call: Call<GetQrResponseModel>, t: Throwable) {

            }

        })
    }

}