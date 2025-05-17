package com.paradoxo.materialgram.di

import android.content.Context
import com.paradoxo.materialgram.utils.PermissionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PermissionUtilsModule {
    @Provides
    fun providePermissionUtils(@ApplicationContext context: Context): PermissionUtils {
        return PermissionUtils(context)
    }
}