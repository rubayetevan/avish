package com.avish.admin.useCase

import com.avish.admin.common.utility.Resource
import com.avish.admin.models.SessionData
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun doLoginAndCreateSession(userName:String,password:String):Flow<Resource<Boolean>>
}