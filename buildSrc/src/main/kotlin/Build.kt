object Build {
    const val  appVersionName = "1"
    const val appVersionCode = 1
    const val androidMinSdkVersion = 21
    const val androidCompileSdkVersion = 32
    const val androidTargetSdkVersion = 31
    const val applicationId = "com.goplay.test.angga"

    private const val androidBuildToolsVersion = "7.2.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    private const val hiltAndroidGradlePluginVersion = "2.43.2"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"
}