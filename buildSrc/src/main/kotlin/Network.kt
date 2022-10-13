object Network {
    val Deps = listOf(
        // coroutine
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}",

        // lifecycle
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}",

        // network
        "com.squareup.retrofit2:retrofit:${Version.retrofit}",
        "com.squareup.okhttp3:okhttp:${Version.okHttp}",

        // utility
        "com.squareup.retrofit2:converter-gson:${Version.retrofitGson}",
        "com.squareup.retrofit2:converter-scalars:${Version.retrofitScalar}",

        // chucker
        "com.github.chuckerteam.chucker:library:${Version.chucker}"
    )
}