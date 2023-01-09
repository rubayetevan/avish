package com.avish.admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avish.admin.common.utility.Resource
import com.avish.admin.models.SessionData
import com.avish.admin.useCase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    var _userNameInput = MutableStateFlow(String())
    val userName = _userNameInput.asStateFlow()

    var _passwordInput = MutableStateFlow(String())
    val password = _passwordInput.asStateFlow()

    fun doLogin() {
        viewModelScope.launch {
            authUseCase.doLoginAndCreateSession(userName.value, password.value).collect {
                when (it) {
                    is Resource.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                data = it.data,
                                error = false,
                                message = null
                            )
                        }
                        getUserLoginStatus()
                    }
                    is Resource.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                data = null,
                                error = true,
                                message = it.message,
                            )
                        }
                    }
                    is Resource.Empty -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                data = null,
                                error = true,
                                message = it.message ?: "Empty Resource",
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = true,
                                data = null,
                                error = false,
                                message = null
                            )
                        }
                    }
                }
            }
        }
    }


    fun getUserLoginStatus() {
        viewModelScope.launch {
            authUseCase.getUserLoginStatus().collect {
                if (it is Resource.Success) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isUserLoggedIn = it.data ?: false
                        )
                    }
                }
            }
        }
    }

    fun doLogOut() {
        viewModelScope.launch {
            authUseCase.logOut().collect {
                when (it) {
                    is Resource.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                data = null,
                                error = false,
                                isUserLoggedIn = false,
                                message = "Logout Success",
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                error = true,
                                message = it.message
                            )
                        }
                    }
                    is Resource.Empty -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                error = true,
                                message = it.message ?: "Empty Resource",
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loading = true,
                                error = false,
                                message = null
                            )
                        }
                    }
                }
            }
        }
    }

}

data class AuthUiState(
    val loading: Boolean = false,
    val data: SessionData? = null,
    val isUserLoggedIn: Boolean = false,
    val error: Boolean = false,
    val message: String? = null
)