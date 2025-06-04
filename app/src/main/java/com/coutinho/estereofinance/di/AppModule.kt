package com.coutinho.estereofinance.di

import android.content.Context
import com.coutinho.estereofinance.data.local.db.AppDatabase
import com.coutinho.estereofinance.data.local.dao.*
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideSubcategoryDao(database: AppDatabase): SubcategoryDao {
        return database.subcategoryDao()
    }

    @Provides
    fun provideMovimentDao(database: AppDatabase): MovimentDao {
        return database.movimentDao()
    }
}