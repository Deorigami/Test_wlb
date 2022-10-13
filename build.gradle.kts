// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath("org.jacoco:org.jacoco.core:0.8.7")
        classpath("com.hiya:jacoco-android:0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.3")
    }
}

plugins {
    id("jacoco")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}