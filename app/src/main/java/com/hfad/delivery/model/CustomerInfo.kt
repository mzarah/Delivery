package com.hfad.delivery.model


import com.google.gson.annotations.SerializedName

data class CustomerInfo(
        @SerializedName("addresses")
    val addresses: List<Addresses>,
        @SerializedName("birthDate")
    val birthDate: String,
        @SerializedName("candidateEmail")
    val candidateEmail: Any,
        @SerializedName("currentLatitude")
    val currentLatitude: Any,
        @SerializedName("currentLongitude")
    val currentLongitude: Any,
        @SerializedName("currentPromoCode")
    val currentPromoCode: CurrentPromoCode,
        @SerializedName("customerUniqueString")
    val customerUniqueString: String,
        @SerializedName("defaultPaymentMethod")
    val defaultPaymentMethod: String,
        @SerializedName("email")
    val email: String,
        @SerializedName("fullName")
    val fullName: String,
        @SerializedName("gender")
    val gender: String,
        @SerializedName("generatedPromoCode")
    val generatedPromoCode: GeneratedPromoCode,
        @SerializedName("id")
    val id: Int,
        @SerializedName("linkedSocialAccount")
    val linkedSocialAccount: Any,
        @SerializedName("phoneNumber")
    val phoneNumber: String,
        @SerializedName("registered")
    val registered: Boolean,
        @SerializedName("socialAccountUid")
    val socialAccountUid: Any,
        @SerializedName("status")
    val status: String,
        @SerializedName("verificationCode")
    val verificationCode: Any,
        @SerializedName("verified")
    val verified: Any
)