package com.avish.admin.useCase

import android.util.Log
import com.avish.admin.common.utility.Resource
import com.avish.admin.common.utility.session.Session
import com.avish.admin.models.SessionData
import com.avish.admin.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: Repository,
    private val session: Session<SessionData>
) : AuthUseCase {
    override suspend fun doLoginAndCreateSession(
        userName: String,
        password: String
    ): Flow<Resource<SessionData>> = repository.doLogin(userName, password).onEach {
        if (it is Resource.Success) {
            it.data?.let { data ->
                session.createSession(data)
            } ?: run {
                Log.d("AuthUseCaseImpl", "Session data is missing")
            }
        }
    }

    override suspend fun getSessionData(): Flow<Resource<SessionData>> = flow {
        val sessionData = session.getSessionData()
        emit(Resource.Success(sessionData))
    }

    override suspend fun logOut(): Flow<Resource<Boolean>> = flow {
        session.logOut()
        emit(Resource.Success(true))
    }

    override suspend fun getUserLoginStatus(): Flow<Resource<Boolean>> = flow {
        val data = session.isLoggedIn()
        emit(Resource.Success(data))
    }


}