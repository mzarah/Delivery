package com.hfad.delivery.repository

import com.hfad.delivery.api.RetrofitInstance
import com.hfad.delivery.model.CustomerInfo
import com.hfad.delivery.model.PhoneNOtp
import com.hfad.delivery.model.UserToken
import com.hfad.delivery.model.phoneNumber
import io.reactivex.Completable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call

class Repository {

    suspend fun getOtp(phoneNumber: phoneNumber): retrofit2.Response<Any?> {
        return RetrofitInstance.api.getOtp(phoneNumber)
    }

    suspend fun authenticate(phoneNOtp: PhoneNOtp): retrofit2.Response<UserToken> {
        return RetrofitInstance.api.authenticate(phoneNOtp)
    }

    suspend fun getCustomerInfo(headers: Map<String, String>): retrofit2.Response<CustomerInfo> {
        return RetrofitInstance.api.getCustomerInfo(headers)
    }

    suspend fun addPromoCode(code: String, headers: Map<String, String>): ResponseBody {
        return RetrofitInstance.api.addPromoCode(code, headers)
    }
}