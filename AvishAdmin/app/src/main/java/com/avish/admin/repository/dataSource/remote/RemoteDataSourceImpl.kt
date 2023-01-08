package com.avish.admin.repository.dataSource.remote

import com.avish.admin.common.api.AvishRestApi
import com.avish.admin.common.utility.Resource
import com.avish.admin.models.LoginRequestModel
import com.avish.admin.models.SessionData
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val avishRestApi: AvishRestApi) :
    RemoteDataSource {
    override suspend fun doLogin(loginRequestModel: LoginRequestModel): Resource<SessionData> {
        return try {
            val response = avishRestApi.doLogin(loginRequestModel)
            if (response.code()<400 && response.code()!=204 && response.body()!=null) {
                Resource.Success(response.body()!!)
            } else if (response.code()==204 && response.body() == null) {
                Resource.Empty()
            } else if(response.code() in 400..499){
                Resource.Error(response.body()?.errorMessage!!)
            }else{
                Resource.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Could not get data from server.")
        }
    }
}