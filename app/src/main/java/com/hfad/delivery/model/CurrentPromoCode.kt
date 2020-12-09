package com.hfad.delivery.model


import com.google.gson.annotations.SerializedName

data class CurrentPromoCode(
    @SerializedName("code")
    val code: String,
    @SerializedName("customerIdThatCanUse")
    val customerIdThatCanUse: Any,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("forFirstOrder")
    val forFirstOrder: Boolean,
    @SerializedName("generatedForCustomer")
    val generatedForCustomer: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ownerId")
    val ownerId: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("usageLimit")
    val usageLimit: Int,
    @SerializedName("validation")
    val validation: String
)