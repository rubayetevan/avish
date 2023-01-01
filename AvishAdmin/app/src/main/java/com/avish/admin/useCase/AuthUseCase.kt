package com.avish.admin.useCase

import com.avish.admin.common.utility.Resource
import com.avish.admin.models.SessionData
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun doLoginAndCreateSession(
        userName: String,
        password: String
    ): Flow<Resource<SessionData>>

    suspend fun getSessionData(): Flow<Resource<SessionData>>
    suspend fun logOut(): Flow<Resource<Boolean>>
    suspend fun getUserLoginStatus(): Flow<Resource<Boolean>>
}