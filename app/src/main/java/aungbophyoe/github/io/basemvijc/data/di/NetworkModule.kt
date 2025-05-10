package aungbophyoe.github.io.basemvijc.data.di

import android.content.Context
import aungbophyoe.github.io.basemvijc.data.remote.ApiService
import aungbophyoe.github.io.basemvijc.data.remote.NetworkMonitor
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.ErrorHandlingInterceptor
import aungbophyoe.github.io.basemvijc.data.remote.interceptors.HeaderInterceptor
import com.google.gson.GsonBuilder
import com.lomawebview.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val WRITE_TIME = 60L
    private const val READ_TIME = 60L
    private const val CONNECT_TIME = 60L
    private const val CONTENT_LENGTH = 250_000L

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }

    @Provides
    @Singleton
    fun provideExceptionHandler(): ErrorHandlingInterceptor {
        return ErrorHandlingInterceptor()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        errorHandlingInterceptor: ErrorHandlingInterceptor,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder().apply {
                    writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                    readTimeout(READ_TIME, TimeUnit.SECONDS)
                    connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                    addInterceptor(headerInterceptor)
                    addInterceptor(errorHandlingInterceptor)
                    if (BuildConfig.DEBUG) {
                        addInterceptor(loggingInterceptor)
                    }
                }.build()
            )
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setPrettyPrinting()
                        .setLenient()
                        .create()
                )
            ).build()
    }
}