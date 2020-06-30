package com.leunesmedia.tappbe.di.modules

import android.app.Application
import com.leunesmedia.tappbe.data.db.TappDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class TappDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = TappDatabase.getTappDatabaseInstance(application)

    @Singleton
    @Provides
    fun provideBeerDao(database: TappDatabase) = database.beerDao()
}