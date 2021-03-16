package com.example.tatvasoft.models

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("data")
    val data: Users
)