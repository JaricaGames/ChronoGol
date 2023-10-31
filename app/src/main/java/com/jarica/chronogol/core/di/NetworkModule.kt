package com.jarica.chronogol.core.di

import com.jarica.chronogol.core.Config
import com.jarica.chronogol.data.PuntuationDataSource
import com.jarica.chronogol.data.repository.PuntuationRepository
import com.jarica.chronogol.data.repository.impl.PuntuationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSupabaseClient() : SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = Config.SUPABASE_URL,
            supabaseKey = Config.SUPABASE_KEY,
        ){
            install(Postgrest)
        }
    }

  /*  @Provides
    @Singleton
    fun providePuntuationDataSource(client: SupabaseClient): PuntuationDataSource{
        return PuntuationDataSource(client)
    }
    @Provides
    @Singleton
    fun providePuntuationRepository( dataSource: PuntuationDataSource): PuntuationRepositoryImpl{
        return PuntuationRepositoryImpl(dataSource)
    }*/

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }
}