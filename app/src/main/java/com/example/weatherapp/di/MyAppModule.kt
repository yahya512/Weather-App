package com.example.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.core.AppSharedPreferences
import com.example.weatherapp.core.ENGLISH
import com.example.weatherapp.home.data.remote.API_KEY
import com.example.weatherapp.home.data.remote.GetWeatherDetailsApi
import com.example.weatherapp.home.data.repository.GetWeatherRepositoryImpl
import com.example.weatherapp.home.domain.repository.GetWeatherDetailsRepository
import com.example.weatherapp.home.domain.usecase.GetWeatherDetailsUseCase
import com.example.weatherapp.search.data.remote.SearchResultApi
import com.example.weatherapp.search.data.repository.SearchRepositoryImpl
import com.example.weatherapp.search.domain.repository.SearchRepository
import com.example.weatherapp.search.domain.usecase.SearchByCityUseCase
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
        sharedPreferences: AppSharedPreferences
    ): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chuker)
                .addInterceptor { chain ->
                    val key = chain.request()
                        .newBuilder()
                        .addHeader("key", API_KEY)
                        .addHeader("lang", sharedPreferences.getLanguage() ?: ENGLISH)
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
        sharedPreference: AppSharedPreferences
    ): GetWeatherDetailsRepository {
        return GetWeatherRepositoryImpl(apiService, sharedPreference)
    }

    //provide SharedPreferences
    @Provides
    fun provideLocationLocalDataSource(sharedPreference: SharedPreferences): AppSharedPreferences {
        return AppSharedPreferences(sharedPreference)
    }

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "myPref", Context.MODE_PRIVATE
        )
    }

    // Provide Repository for SearchUseCase
    @Provides
    fun provideRepositoryForSearchUseCase(repository: SearchRepository): SearchByCityUseCase {
        return SearchByCityUseCase(repository)
    }

    // Create Instance for Search Api
    @Provides
    fun provideInstanceForSearchApi(retrofit: Retrofit): SearchResultApi {
        return retrofit.create(SearchResultApi::class.java)
    }

    //Provide Api Service to Repo Impl
    @Provides
    fun provideApiServiceForSearchRepoImpl(apiService: SearchResultApi): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

}