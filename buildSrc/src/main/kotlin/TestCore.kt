object TestCore {
    val androidTest = listOf(
        "androidx.test:core-ktx:${Version.Test.coreTestingVersion}",
        "androidx.arch.core:core-testing:${Version.Test.androidArchVersion}",
        "androidx.test.ext:junit-ktx:${Version.Test.androidJUnitVersion}",
        "androidx.test.espresso:espresso-core:${Version.Test.androidEspressoVersion}",
        "androidx.room:room-testing:${Version.room}",

    )
    val Deps = listOf(
        "org.junit.jupiter:junit-jupiter-api:${Version.Test.jUnitVersion}",
        "org.junit.jupiter:junit-jupiter-engine:${Version.Test.jUnitVersion}",
        "org.mockito:mockito-core:${Version.Test.mockitoCoreVersion}",
        "org.mockito:mockito-junit-jupiter:${Version.Test.mockitoCoreVersion}",
        "org.mockito:mockito-inline:${Version.Test.mockitoCoreVersion}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Test.coroutineTestVersion}",
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.Test.mockitoKotlinVersion}",
        "org.jacoco:org.jacoco.agent:0.8.5"
    )
}