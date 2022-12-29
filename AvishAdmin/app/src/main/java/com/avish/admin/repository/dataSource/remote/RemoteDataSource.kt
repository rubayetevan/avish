package com.avish.admin.repository.dataSource.remote

import com.avish.admin.common.utility.Resource
import com.avish.admin.models.LoginRequestModel
import com.avish.admin.models.SessionData

interface RemoteDataSource {
    suspend fun doLogin(loginRequestModel: LoginRequestModel): Resource<SessionData>
}