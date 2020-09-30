package com.example.tokenpaymentdemo.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tokenpaymentdemo.repository.FetchApiQrRepository
import com.example.tokenpaymentdemo.repository.PaymentRepository
import com.example.tokenpaymentdemo.viewmodel.GetQrViewModel
import com.example.tokenpaymentdemo.viewmodel.PaymentViewModel


class ViewModelFactory(
    private val repository: FetchApiQrRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GetQrViewModel::class.java) -> {
                GetQrViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Not Assignable Class!")
            }
        }
    }
}

class PaymentViewModelFactory(
    private val repository: PaymentRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PaymentViewModel::class.java) -> {
                PaymentViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Not Assignable Class!")
            }
        }
    }
}