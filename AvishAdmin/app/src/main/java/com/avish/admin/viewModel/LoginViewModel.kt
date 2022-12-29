package com.avish.admin.viewModel

import androidx.lifecycle.ViewModel
import com.avish.admin.common.utility.Resource
import com.avish.admin.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    suspend fun doLogin(): Flow<Resource<Boolean>> =
        loginUseCase.doLoginAndCreateSession("rubayet", "123456")


}