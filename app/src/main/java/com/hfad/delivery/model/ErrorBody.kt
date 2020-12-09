package com.hfad.delivery.model


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("entityName")
    val entityName: String,
    @SerializedName("errorKey")
    val errorKey: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("params")
    val params: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)