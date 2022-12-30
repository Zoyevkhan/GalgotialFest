package com.example.galgotialfest.dagger

import android.content.Context
import android.content.SharedPreferences
import com.example.galgotialfest.database.PreferenceManager
import com.example.galgotialfest.repos.SplashReposatory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context:Context):PreferenceManager{
        return PreferenceManager(context)
    }

    @Provides
    @Singleton
    fun providePrefereeManager(@ApplicationContext context:Context):SplashReposatory{
        return SplashReposatory()
    }

}