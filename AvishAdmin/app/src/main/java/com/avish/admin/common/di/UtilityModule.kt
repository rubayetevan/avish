package com.avish.admin.common.di

import com.avish.admin.common.utility.session.Session
import com.avish.admin.common.utility.session.SessionImpl
import com.avish.admin.models.SessionData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilityModule {

    @Singleton
    @Binds
    abstract fun bindSession(
        sessionImpl: SessionImpl
    ): Session<SessionData>
}