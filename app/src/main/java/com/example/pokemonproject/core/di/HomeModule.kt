package com.example.pokemonproject.core.di

import com.example.pokemonproject.data.HomeApi
import com.example.pokemonproject.data.HomeRepositoryImpl
import com.example.pokemonproject.domain.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    fun homeRepo(homeApi: HomeApi): HomeRepo {
        return HomeRepositoryImpl(homeApi = homeApi)
    }
}