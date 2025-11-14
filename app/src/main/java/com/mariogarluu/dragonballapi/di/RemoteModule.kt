package com.mariogarluu.dragonballapi.di

import com.mariogarluu.dragonballapi.Data.Remote.DragonBallApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proveer las dependencias relacionadas con la red.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    /**
     * Provee una instancia singleton de Retrofit.
     *
     * @return Una instancia de [Retrofit].
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provee una instancia singleton de la API de Dragon Ball.
     *
     * @param retrofit La instancia de Retrofit.
     * @return Una instancia de [DragonBallApi].
     */
    @Provides
    @Singleton
    fun provideDragonBallApi(retrofit: Retrofit): DragonBallApi {
        return retrofit.create(DragonBallApi::class.java)
    }
}