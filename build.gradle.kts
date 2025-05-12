// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val nav_version = "2.8.9"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    //ksp - https://developer.android.com/build/migrate-to-ksp#add-ksp
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.dagger.hilt.android") version "2.56.1" apply false

    // * Enables Kotlin Compilation for JVM: The core function of this plugin is to enable the compilation
// * of Kotlin source code for the JVM. When apply this plugin, Gradle becomes aware of
// * Kotlin sources (.kt files) and sets up the necessary tasks to compile them into JVM bytecode (.class files)
// *
// * Integrates with Gradle: The plugin integrates seamlessly with Gradle's build lifecycle.
// * It adds tasks like compileKotlin (for compiling main source sets) and
// * compileTestKotlin (for compiling test source sets) to the project.
// *
// * Manages Kotlin Dependencies: It helps manage Kotlin-related dependencies, such as the Kotlin standard library.
// *
// * Configures Source Sets: By default, the plugin expects Kotlin source files to be located in
// * the src/*/kotlin directories (e.g., src/main/kotlin, src/test/kotlin), similar to how Java sources are in src/*/java.
// *
// * Compatibility with Java: The Kotlin JVM plugin allows to have both Kotlin and
// * Java source files in the same project. Can even have them in the same source set
// * (though the convention is to keep them in separate kotlin and java directories).
// *
// * Requires a Kotlin Version: When apply the plugin, need to specify the Kotlin version to use
// *
// * Use in Non-Android JVM Projects: This plugin is commonly used for building applications that
// * run on the JVM but are not Android applications (e.g., backend services, desktop applications, command-line tools).
// * In the context of an Android project, typically use the kotlin("android") plugin
// * (or the com.android.application / com.android.library plugins along with the Kotlin plugin applied differently).
// * However, in a multi-module Android project, might have a pure Kotlin JVM module that contains
// * shared logic or data models that don't depend on the Android framework.
// * In such modules, would use the kotlin("jvm") plugin.
// *
// * In summary, kotlin("jvm") is the essential Gradle plugin for compiling and
// * building Kotlin code that targets the Java Virtual Machine.
    kotlin("jvm") version "2.1.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
