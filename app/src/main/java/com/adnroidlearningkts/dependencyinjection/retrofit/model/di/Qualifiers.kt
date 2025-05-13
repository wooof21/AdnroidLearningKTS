package com.adnroidlearningkts.dependencyinjection.retrofit.model.di

import javax.inject.Qualifier

class Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MovieRetrofit

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class PostsRetrofit
}