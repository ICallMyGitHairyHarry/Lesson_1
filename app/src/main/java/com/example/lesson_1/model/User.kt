package com.example.lesson_1.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: address,
    val phone: String,
    val website: String,
    val company: company
)

data class address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: geo
)

data class geo(
    val lat: String,
    val lng: String
)

data class company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)