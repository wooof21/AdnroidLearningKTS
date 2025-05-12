package com.adnroidlearningkts.dependencyinjection.retrofit.model.di

import com.adnroidlearningkts.BuildConfig
import com.adnroidlearningkts.dependencyinjection.firebase.repository.DataRepo
import com.adnroidlearningkts.dependencyinjection.retrofit.model.config.PostsInterface
import com.adnroidlearningkts.dependencyinjection.retrofit.model.repository.PostRepo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            // Log requests and responses only in debug builds
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Example: Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Example: Set read timeout
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        // Configure Gson if needed (e.g., date formats, custom adapters)
        return GsonBuilder()
            // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * When there are multiple BASE_URL:
     *
     * 1. Define Different API Service Interfaces: Create separate Retrofit service interfaces for
     *      each API endpoint or group of related endpoints that share a base URL.
     * 2. Create Different Retrofit Instances: Provide separate Retrofit instances in the Hilt module(s),
     *      each configured with a specific base URL.
     * 3. Provide Each Retrofit Instance with a Qualifier: Use Hilt's @Qualifier annotation to
     *      distinguish between the different Retrofit instances.
     * 4. Inject the Correct Retrofit Instance: Inject the specific Retrofit instance
     *      (using its qualifier) wherever need to create a service interface for that base URL.
     *
     *      e.g.
     *      Qualifiers.kt: define the Qualifier annotations for each Retrofit instance
     *
     *      @Qualifier //create custom annotation as a Hilt qualifier.
     *      @Retention(AnnotationRetention.RUNTIME) // make the annotation is available at runtime, which Hilt needs to use it for dependency resolution
     *      annotation class MovieRetrofit //label to distinguish the Retrofit instances
     *
     *      @Qualifier
     *      @Retention(AnnotationRetention.RUNTIME) // Use RUNTIME retention
     *      annotation class PostsRetrofit
     *
     *      to Use in Module class:
     *
     *      @MovieRetrofit
     *      @Provides
     *      @Singleton
     *      fun provideMovieRetrofit(..)
     *
     *      @PostsRetrofit
     *      @Provides
     *      @Singleton
     *      fun providePostsRetrofit(..)
     *
     *
     *      @Provides
     *      @Singleton
     *      fun provideMovieApi(@MovieRetrofit retrofit: Retrofit)
     *
     *      @Provides
     *      @Singleton
     *      fun providePostsApi(@PostsRetrofit retrofit: Retrofit)
     */

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): PostsInterface {
        return retrofit.create(PostsInterface::class.java)
    }
}