package com.example.myapplication.ui.main

import com.example.myapplication.domain.model.User

data class MainUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null
)
