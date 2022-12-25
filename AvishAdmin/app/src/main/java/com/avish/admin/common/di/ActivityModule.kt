package com.avish.admin.common.di

import android.content.Context
import com.avish.admin.common.utility.AvishSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideSessionManager(@ApplicationContext appContext: Context): AvishSessionManager {
        return AvishSessionManager(appContext)
    }
}