package com.avish.admin.common.di

import com.avish.admin.common.api.AvishRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Singleton
    @Provides
    fun provideAvishRestAPI(retrofit: Retrofit): AvishRestApi {
        return retrofit.create(AvishRestApi::class.java)
    }
}