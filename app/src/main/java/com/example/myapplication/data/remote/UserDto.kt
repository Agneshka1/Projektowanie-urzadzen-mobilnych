package com.example.myapplication.data.remote

import com.example.myapplication.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)

fun UserDto.toDomain(): User = User(id = id, name = name, email = email)
