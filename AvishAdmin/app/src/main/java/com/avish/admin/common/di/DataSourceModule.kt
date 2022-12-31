package com.avish.admin.common.di

import com.avish.admin.repository.Repository
import com.avish.admin.repository.RepositoryImpl
import com.avish.admin.repository.dataSource.remote.RemoteDataSource
import com.avish.admin.repository.dataSource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}