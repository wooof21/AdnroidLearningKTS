plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //Google is phasing out dataBinding in favor of compose and KSP support for Data Binding is not planned.
    // Therefore the use of kotlin-kapt plugin is still required.
    id("kotlin-kapt")
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"

    id("androidx.navigation.safeargs.kotlin")
    //ksp - https://developer.android.com/build/migrate-to-ksp#add-ksp
    //Ksp list of supported libraries - https://kotlinlang.org/docs/ksp-overview.html#supported-libraries
    id("com.google.devtools.ksp")

    id("kotlin-parcelize")

    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")

    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "com.adnroidlearningkts"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.adnroidlearningkts"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"DEBUG_API_KEY\"")
            // ...
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "API_KEY", "\"PRODUCTION_API_KEY\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.appcompat.v161)
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //coroutines - https://developer.android.com/kotlin/coroutines
    implementation(libs.kotlinx.coroutines.android)

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    /*---------------------JetPack ViewModel----------------------*/
    val lifecycle_version = "2.8.7"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // Annotation processor
    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    /*---------------------------------------------------------------*/

    //JetPack Swiperefreshlayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-beta01")


    /*---------------------JetPack Navigation------------------------*/
    val nav_version = "2.8.9"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // JSON serialization library, works with the Kotlin serialization plugin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    /*---------------------------------------------------------------*/

    /*---------------------Android Room------------------------*/
    val room_version = "2.7.1"

    implementation("androidx.room:room-runtime:$room_version")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    /*---------------------------------------------------------------*/

    /*---------------------Android Retrofit------------------------*/
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //Retrofit Converters: serialization libraries
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    //OKHttp Logging Interceptor - https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    /*---------------------------------------------------------------*/

    /*---------------------Android Firebase------------------------*/
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-database")
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")
    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")
    // Add the dependency for the Cloud Storage library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-storage")
    /*---------------------------------------------------------------*/

    /*-----------------------Dependency Injection - Hilt-----------------*/
    //https://developer.android.com/training/dependency-injection/hilt-android
    implementation("com.google.dagger:hilt-android:2.56.1")
    ksp("com.google.dagger:hilt-android-compiler:2.56.1")
    /*------------------------------------------------------------------*/

    /*-----------------------Android JetPack Paging-----------------*/
    val paging_version = "3.3.6"
    implementation("androidx.paging:paging-runtime:$paging_version")
    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common:$paging_version")
    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$paging_version")
    /*------------------------------------------------------------------*/

    /*-----------------------Android JetPack Compose-----------------*/
    val composeBom = platform("androidx.compose:compose-bom:2025.05.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //SystemUiController from the Accompanist System UI Controller library
    //https://google.github.io/accompanist/systemuicontroller/
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")
    /*------------------------------------------------------------------*/

}
