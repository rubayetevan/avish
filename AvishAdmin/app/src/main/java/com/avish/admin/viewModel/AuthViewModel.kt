package com.avish.admin.viewModel

import androidx.lifecycle.ViewModel
import com.avish.admin.common.utility.Resource
import com.avish.admin.useCase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    suspend fun doLogin(): Flow<Resource<Boolean>> =
        authUseCase.doLoginAndCreateSession("rubayet", "123456")

    suspend fun isUserLoggedIn(): Flow<Resource<Boolean>> =
        authUseCase.isUserLoggedIn()

}