buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath ("com.android.tools.build:gradle:8.5.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.5.21")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.devtoolsKsp)
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}