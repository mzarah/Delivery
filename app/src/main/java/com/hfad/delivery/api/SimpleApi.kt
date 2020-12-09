package com.hfad.delivery.api

import com.hfad.delivery.model.CustomerInfo
import com.hfad.delivery.model.PhoneNOtp
import com.hfad.delivery.model.UserToken
import com.hfad.delivery.model.phoneNumber
import io.reactivex.Completable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @POST("api/customers/otp-code")
    suspend fun getOtp(
        @Body phoneNumber: phoneNumber
    ): Response<Any?>

    @POST("api/customers/otp-verification")
    suspend fun authenticate(
        @Body phoneNOtp: PhoneNOtp
    ): Response<UserToken>

    @GET("api/customers/current")
    suspend fun getCustomerInfo(
        @HeaderMap headers: Map<String, String>
    ): Response<CustomerInfo>

    @PUT("api/promo-codes/addition/{code}")
    suspend fun addPromoCode(
            @Path("code") code: String,
            @HeaderMap headers: Map<String, String>
    ): ResponseBody

}



