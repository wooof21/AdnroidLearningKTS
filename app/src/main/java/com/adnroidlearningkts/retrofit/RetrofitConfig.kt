package com.adnroidlearningkts.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * If the companion object only serves the purpose of holding and providing this Retrofit instance,
 * can make the object itself the singleton
 *  - Access the instance the same via RetrofitClient.instance.
 *
 * `object keyword` is used to declare a singleton.
 *      * Single Instance: A single instance of the object is created the first time it's accessed.
 *      * No Constructor: Objects cannot have constructors because you don't explicitly create instances of them using a constructor call.
 *      * Direct Access: access members (properties and functions) of an object directly using its name, without needing to create an instance.
 */
object RetrofitConfig {
//class RetrofitConfig {

//    companion object {
        internal const val BASE_URL = "https://jsonplaceholder.typicode.com"

        /**
         * If `getInstance` function is called from multiple threads concurrently, it could
         * potentially create multiple Retrofit instances, which is usually not desired for
         * a singleton pattern.
         *
         * Using a `lazy` property ensures that the Retrofit instance is created only once,
         * the first time it's accessed, and in a thread-safe manner.
         *
         * How to use: Instead of calling RetrofitConfig.getInstance(),
         * now access RetrofitConfig.instance.
         *
         */
//        fun getInstance(): Retrofit {
        val instance: Retrofit by lazy { // Use a lazy property
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Or Level.BASIC, Level.HEADERS
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
//    }
}