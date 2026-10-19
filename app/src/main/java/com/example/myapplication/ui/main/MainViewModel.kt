package com.example.myapplication.ui.main

import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.UserRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : BaseViewModel<MainUiState>(MainUiState()) {

    init {
        observeUsers()
        refreshData()
    }

    private fun observeUsers() {
        userRepository.getUsers()
            .onEach { userList ->
                updateState { copy(users = userList, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    fun refreshData() {
        viewModelScope.launch {
            updateState { copy(isLoading = true, errorMessage = null) }
            try {
                userRepository.refreshUsers()
            } catch (e: Exception) {
                updateState { copy(isLoading = false, errorMessage = e.localizedMessage) }
            }
        }
    }
}
