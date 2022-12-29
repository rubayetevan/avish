package com.avish.admin.repository

import com.avish.admin.common.utility.Resource
import com.avish.admin.models.SessionData
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun doLogin(userName:String,password:String): Flow<Resource<SessionData>>
}