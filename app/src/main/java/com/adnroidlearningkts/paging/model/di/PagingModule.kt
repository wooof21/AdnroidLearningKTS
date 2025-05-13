package com.adnroidlearningkts.paging.model.di

import android.content.Context
import com.adnroidlearningkts.BuildConfig
import com.adnroidlearningkts.R
import com.adnroidlearningkts.dependencyinjection.retrofit.model.config.PostsInterface
import com.adnroidlearningkts.dependencyinjection.retrofit.model.di.Qualifiers.MovieRetrofit
import com.adnroidlearningkts.paging.model.apiinterface.PMovieInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = "https://api.themoviedb.org/3/"


@Module
@InstallIn(SingletonComponent::class)
class PagingModule {


    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context).applyDefaultRequestOptions(
            RequestOptions()
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
        )
    }

    @MovieRetrofit
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            // Log requests and responses only in debug builds
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }


    @MovieRetrofit
    @Provides
    @Singleton
    fun provideOkHttpClient(@MovieRetrofit loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val originalReq = it.request()
                val originalHttpUrl = originalReq.url
                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "76df037c3dc6f00226909bdc3fb75127")
                    .build()
                val request = originalReq.newBuilder().url(url).build()
                it.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS) // Example: Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Example: Set read timeout
            .build()
    }

    @MovieRetrofit
    @Provides
    @Singleton
    fun provideGson(): Gson {
        // Configure Gson if needed (e.g., date formats, custom adapters)
        return GsonBuilder()
            // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @MovieRetrofit
    @Provides
    @Singleton
    fun provideGsonConverterFactory(@MovieRetrofit gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @MovieRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@MovieRetrofit client: OkHttpClient, @MovieRetrofit gsonFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(@MovieRetrofit retrofit: Retrofit): PMovieInterface {
        return retrofit.create(PMovieInterface::class.java)
    }

}