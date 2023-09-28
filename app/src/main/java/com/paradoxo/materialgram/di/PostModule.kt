package com.paradoxo.materialgram.di

import com.paradoxo.materialgram.data.PostLocalDataSource
import com.paradoxo.materialgram.data.PostRepository
import com.paradoxo.materialgram.data.PostRepositoryImpl
import com.paradoxo.materialgram.domain.PostUseCase
import com.paradoxo.materialgram.domain.PostUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Provides
    fun providePostUseCase(): PostUseCase {
        return PostUseCaseImpl(PostRepositoryImpl(PostLocalDataSource()))
    }

    @Provides
    fun providePostRepository(): PostRepository {
        return PostRepositoryImpl(PostLocalDataSource())
    }

    @Provides
    fun providePostLocalDataSource(): PostLocalDataSource {
        return PostLocalDataSource()
    }
}