package com.jarica.chronogol.core.di

import com.jarica.chronogol.data.repository.PuntuationRepository
import com.jarica.chronogol.data.repository.impl.PuntuationRepositoryImpl
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(impl: PuntuationRepositoryImpl): PuntuationRepository
}

