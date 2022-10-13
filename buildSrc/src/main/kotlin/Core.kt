object Core {
    val Deps = listOf(
        "androidx.work:work-runtime:2.7.0-alpha05",
        // main
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}",
        "androidx.appcompat:appcompat:${Version.appCompat}",
        "androidx.core:core-ktx:${Version.core}",
        "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}",
        "com.google.dagger:hilt-android:${Version.hilt}",

        // Language Selection
        "com.github.YarikSOffice:lingver:${Version.lingver}",
        "androidx.multidex:multidex:${Version.multidex}",
        "com.github.danielceinos:Cooper:${Version.userAgentInterceptor}",
        "androidx.legacy:legacy-support-v4:${Version.legacySupportV4}",

        // PlayCore
        "com.google.android.play:core:${Version.playCore}",

        // PlayCoreCtx
        "com.google.android.play:core:${Version.playCoreCtx}",
        "at.favre.lib:bytes:${Version.bytes}",
        // FB SDK
        "com.facebook.android:facebook-android-sdk:${Version.facebook}",

        // TransactionTooLargeException detector
        "com.gu.android:toolargetool:${Version.tooLargeTool}",

        // CameraX
          "androidx.camera:camera-core:${Version.cameraCore}",
          "androidx.camera:camera-camera2:${Version.camera2}",
          "androidx.camera:camera-lifecycle:${Version.cameraLifecycle}",
          "androidx.camera:camera-view:${Version.cameraView}",

        // TextRecognition (OCR)
          "com.google.android.gms:play-services-mlkit-text-recognition:${Version.mlkitTextRecognition}",

        // Barcode Scanning
          "com.google.mlkit:barcode-scanning:${Version.barcodeScanning}",
    )
}