package com.avish.admin.repository

import com.avish.admin.common.utility.Resource
import com.avish.admin.models.LoginRequestModel
import com.avish.admin.models.SessionData
import com.avish.admin.repository.dataSource.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val externalScope: CoroutineScope,
    private val remoteDataSource: RemoteDataSource,
) : Repository {
    override suspend fun doLogin(userName: String, password: String): Flow<Resource<SessionData>> {
        return flow {
            emit(Resource.Loading())
            val response = withContext(externalScope.coroutineContext) {
                val loginRequestModel = LoginRequestModel(userName, password)
                remoteDataSource.doLogin(loginRequestModel)
            }
            emit(response)
        }
    }

}