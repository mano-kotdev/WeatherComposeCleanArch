package com.manoj.weatherapp.di

import com.manoj.weatherapp.data.dataSource.Api
import com.manoj.weatherapp.data.repository.ApiRepoImpl
import com.manoj.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().also {
        /*if (BuildConfig.DEBUG) {

        }*/
        it.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        it.writeTimeout(30, TimeUnit.SECONDS)
        it.readTimeout(30, TimeUnit.SECONDS)
        it.connectTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideApiRepoImpl(api: Api): ApiRepoImpl = ApiRepoImpl(api)


    @Provides
    @Singleton
    fun provideGetWeatherUseCase(apiRepoImpl: ApiRepoImpl): GetWeatherUseCase = GetWeatherUseCase(apiRepoImpl)
}