package com.hfad.delivery.model


import com.google.gson.annotations.SerializedName

data class GeneratedPromoCode(
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
    val name: Any,
    @SerializedName("ownerId")
    val ownerId: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("usageLimit")
    val usageLimit: Any,
    @SerializedName("validation")
    val validation: Any
)