package com.hfad.delivery.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.delivery.model.CustomerInfo
import com.hfad.delivery.model.PhoneNOtp
import com.hfad.delivery.model.UserToken
import com.hfad.delivery.model.phoneNumber
import com.hfad.delivery.repository.Repository
import io.reactivex.Completable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call


class UserTokenViewModel(private val repository: Repository): ViewModel() {

    val emptyResponse: MutableLiveData<retrofit2.Response<Any?>> = MutableLiveData()
    val userTokenResponse: MutableLiveData<retrofit2.Response<UserToken>> = MutableLiveData()
    val customerInfoResponse: MutableLiveData<retrofit2.Response<CustomerInfo>> = MutableLiveData()
    val emptyResponse2: MutableLiveData<ResponseBody> = MutableLiveData()


    fun getOtp(phoneNumber: phoneNumber) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: retrofit2.Response<Any?> = repository.getOtp(phoneNumber)
                emptyResponse.postValue(response)
            }
            catch (e: Exception){
                Log.d("UserTokenViewModel", e.message)
            }
        }
    }

    fun authenticate(phoneNumberNOtp: PhoneNOtp) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: retrofit2.Response<UserToken> = repository.authenticate(phoneNumberNOtp)
                userTokenResponse.postValue(response)
            }
            catch (e: Exception){
                Log.d("PostViewModel class", e.message)
            }
        }
    }

    fun getCustomerInfo(headers: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: retrofit2.Response<CustomerInfo> = repository.getCustomerInfo(headers)
                customerInfoResponse.postValue(response)
            }
            catch (e: Exception){
                Log.d("PostViewModel class", e.message)
            }
        }
    }

    fun addPromoCode(code: String, headers: Map<String, String>){
        val response: Call<ResponseBody>? = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: ResponseBody = repository.addPromoCode(code, headers)
                emptyResponse2.postValue(response)
            }
            catch (e: Exception){
                Log.d("PostViewModel class", e.message)
            }

        }
    }
    
}