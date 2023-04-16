package com.sudhanshu.newsapp.di

import com.sudhanshu.newsapp.data.NewsApi
import com.sudhanshu.newsapp.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): NewsApi {
        val api: NewsApi by lazy {
            Retrofit.Builder()
                .baseUrl(Util.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
        return api
    }
}