plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("org.sonarqube")
}

android {
    compileSdk = Build.androidCompileSdkVersion

    defaultConfig {
        applicationId = Build.applicationId
        minSdk = Build.androidMinSdkVersion
        targetSdk = Build.androidTargetSdkVersion
        versionCode = Build.appVersionCode
        versionName = Build.appVersionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(project(":features:feature_dashboard"))
    api(project(":features:core"))
    api(project(":features:feature_util"))
    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")

    TestCore.Deps.forEach { dep ->
        if (dep.contains("androidx")) {
            androidTestImplementation(dep)
        } else testImplementation(dep)
    }
    Core.Deps.forEach { dep ->
        implementation(dep)
    }
    Main.Deps.forEach { dep ->
        implementation(dep)
    }
    UI.Deps.forEach { dep ->
        implementation(dep)
    }
    Network.Deps.forEach { dep ->
        implementation(dep)
    }
    LocalDB.Deps.forEach { dep ->
        implementation(dep)
    }
    KaptGroup.Deps.forEach { dep ->
        kapt(dep)
    }
    Firebase.Deps.forEach { dep ->
        implementation(platform(dep))
    }
}


sonarqube.properties {
    property("sonar.projectName", "com.ardinata.test")
    property("sonar.projectKey", "com.ardinata.test")
    property("sonar.host.url", "http://localhost:8585")
    property("sonar.language", "kotlin")
    property("sonar.sources", "src/main/java/")
    property("sonar.binaries", "build")
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.login", "admin")
    property("sonar.password", "admin")
}