package com.example.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.core.LocationLocalDataSource
import com.example.weatherapp.home.data.remote.API_KEY
import com.example.weatherapp.home.data.remote.GetWeatherDetailsApi
import com.example.weatherapp.home.data.repository.GetWeatherRepositoryImpl
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import com.example.weatherapp.home.domain.usecase.GetWeatherDetailsUseCase
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

@InstallIn(SingletonComponent::class)
@Module
object MyAppModule {

    //Apply Chuker in App
    @Provides
    @Singleton
    fun provideChukerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    //Provide okHttp
    @Provides
    fun provideOkHttpLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    // ProvideOkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        chuker: ChuckerInterceptor,
    ): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chuker)
                .addInterceptor { chain ->
                    val key = chain.request()
                        .newBuilder()
                        .addHeader("key", API_KEY)
                        .build()
                    chain.proceed(key)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        return client
    }

    //Provide retrofit
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val retrofit =
            Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit

    }

    //Provide instance of GetWeatherDetailsApi
    @Provides
    fun provideInstanceForGetWeatherDetailsApi(retrofit: Retrofit): GetWeatherDetailsApi {
        return retrofit.create(GetWeatherDetailsApi::class.java)
    }

    // provide repository for GetWeatherDetailsUseCase
    @Provides
    fun provideRepositoryForGetWeatherDetailsUseCase(repository: GetWeatherDetailsRepository): GetWeatherDetailsUseCase {
        return GetWeatherDetailsUseCase(repository)
    }

    // provide apiService for RepositoryImpl
    @Provides
    fun provideGetWeatherDetailsApiForGetWeatherRepositoryImpl(
        apiService: GetWeatherDetailsApi,
        sharedPreference: LocationLocalDataSource
    ): GetWeatherDetailsRepository {
        return GetWeatherRepositoryImpl(apiService, sharedPreference)
    }

    //provide SharedPreferences
    @Provides
    fun provideLocationLocalDataSource(sharedPreference: SharedPreferences): LocationLocalDataSource {
        return LocationLocalDataSource(sharedPreference)
    }

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "myPref", Context.MODE_PRIVATE
        )
    }
}