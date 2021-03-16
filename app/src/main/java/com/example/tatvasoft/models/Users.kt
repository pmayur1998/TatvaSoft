package com.example.tatvasoft.models

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("users")
    val users: List<User>
)