package com.hfad.delivery.model


import com.google.gson.annotations.SerializedName

data class Addresses(
    @SerializedName("address")
    val address: String,
    @SerializedName("apartment")
    val apartment: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("customerDTO")
    val customerDTO: Any,
    @SerializedName("default")
    val default: Boolean,
    @SerializedName("entrance")
    val entrance: String,
    @SerializedName("floor")
    val floor: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("office")
    val office: Any,
    @SerializedName("title")
    val title: String
)