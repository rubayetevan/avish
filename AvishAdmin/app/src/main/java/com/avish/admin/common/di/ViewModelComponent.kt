package com.avish.admin.common.di

import com.avish.admin.useCase.AuthUseCase
import com.avish.admin.useCase.AuthUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelComponent {

    //@Singleton
    @Binds
    abstract fun bindAuthUseCase(authUseCaseImpl: AuthUseCaseImpl): AuthUseCase
}