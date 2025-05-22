package com.example.mybooks.di

import com.example.mybooks.BuildConfig
import com.example.mybooks.features.booklist.data.remote.ApiService
import com.example.mybooks.features.booklist.data.repository.GetUserBooksRepositoryImpl
import com.example.mybooks.features.booklist.domain.repository.GetUserBooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BuildConfig.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideUserBooksRepository(apiService: ApiService): GetUserBooksRepository =
        GetUserBooksRepositoryImpl(apiService)

}