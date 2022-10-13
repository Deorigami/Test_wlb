plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

apply(from = "../jacoco.gradle")
android {
    compileSdk = Build.androidCompileSdkVersion

    defaultConfig {
        minSdk = Build.androidMinSdkVersion
        targetSdk = Build.androidTargetSdkVersion

//        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release"){
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    UI.Deps.forEach {
        implementation(it)
    }

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")

    kapt("com.android.databinding:compiler:3.1.4")
}