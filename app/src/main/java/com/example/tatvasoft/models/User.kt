package com.example.tatvasoft.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("items")
    val items : List<String>,
)