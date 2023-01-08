package com.avish.admin.common.api

import com.avish.admin.models.LoginRequestModel
import com.avish.admin.models.SessionData
import com.avish.admin.models.TokenRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AvishRestApi {
    @POST("auth/login")
    suspend fun doLogin(@Body loginRequestModel: LoginRequestModel): Response<SessionData>

    @POST("auth/refreshToken")
    suspend fun getNewToken(@Body tokenRequestModel: TokenRequestModel): Response<SessionData>
}