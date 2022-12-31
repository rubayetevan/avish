package com.avish.admin.useCase

import com.avish.admin.common.utility.Resource
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun doLoginAndCreateSession(userName: String, password: String): Flow<Resource<Boolean>>

    suspend fun isUserLoggedIn(): Flow<Resource<Boolean>>
}