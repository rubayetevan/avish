package com.avish.admin.useCase

import com.avish.admin.common.utility.Resource
import com.avish.admin.common.utility.session.Session
import com.avish.admin.models.SessionData
import com.avish.admin.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: Repository,
    private val session: Session<SessionData>
) : AuthUseCase {
    override suspend fun doLoginAndCreateSession(
        userName: String,
        password: String
    ): Flow<Resource<Boolean>> = repository.doLogin(userName, password).transform {
        when (it) {
            is Resource.Success -> {
                it.data?.let { data ->
                    session.createSession(data)
                    emit(Resource.Success(true))
                } ?: run {
                    emit(Resource.Success(false))
                }
            }
            is Resource.Error -> emit(Resource.Error(it.message ?: "Error"))
            is Resource.Loading -> emit(Resource.Loading())
            is Resource.Empty -> emit(Resource.Empty())
        }
    }

    override suspend fun isUserLoggedIn(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        val isUserLoggedIn = session.isLoggedIn()
        emit(Resource.Success(isUserLoggedIn))
    }


}