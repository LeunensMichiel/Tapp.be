package com.leunesmedia.tappbe.di.modules

import com.leunesmedia.tappbe.data.api.BeerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class TappApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService() = Retrofit.Builder()
        .baseUrl("https://sandbox-api.brewerydb.com/v2/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(BeerService::class.java)
}