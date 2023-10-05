package com.paradoxo.materialgram.di

import com.paradoxo.materialgram.data.PostRepository
import com.paradoxo.materialgram.data.PostRepositoryImpl
import com.paradoxo.materialgram.data.local.PostLocalDataSource
import com.paradoxo.materialgram.data.network.PostService
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
    fun providePostUseCase(postRepository: PostRepository): PostUseCase {
        return PostUseCaseImpl(postRepository)
    }

    @Provides
    fun providePostRepository(
        postLocalDataSource: PostLocalDataSource,
        postService: PostService
    ): PostRepository {
        return PostRepositoryImpl(postLocalDataSource, postService)
    }

    @Provides
    fun providePostLocalDataSource(): PostLocalDataSource {
        return PostLocalDataSource()
    }
}