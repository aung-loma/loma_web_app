package aungbophyoe.github.io.basemvijc.domain.di

import aungbophyoe.github.io.basemvijc.data.remote.repository.ConfigRepositoryImpl
import aungbophyoe.github.io.basemvijc.domain.repository.ConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by aungb on 5/10/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
fun interface RepositoryModule {
    @Binds
    @Singleton
    fun bindEComConfigRepository(
        repoImpl: ConfigRepositoryImpl
    ): ConfigRepository
}