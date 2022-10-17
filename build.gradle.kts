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
    id("org.sonarqube") version "3.3"
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}


sonarqube.properties {
    property("sonar.projectName", "com.ardinata.test")
    property("sonar.projectKey", "com.ardinata.test")
    property("sonar.host.url", "http://localhost:8585")
    property("sonar.language", "kotlin")
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.login", "admin")
    property("sonar.password", "ardinata")
    property("sonar.sources", "src/main/java")
    property("sonar.binaries", "build/intermediates/classes/debug")
    property("sonar.java.binaries", "build/intermediates/classes/debug")
    property("sonar.tests", "src/test/java, src/androidTest/java")
    property("sonar.java.test.binaries", "build/intermediates/classes/debug")

    val unit = fileTree(mapOf("dir" to projectDir, "includes" to listOf("**/*.exec"))).files
    property("sonar.jacoco.reportPaths", unit.joinToString(", "))

    val jacocoReportXmlFiles = fileTree(
        mapOf(
            "dir" to project.projectDir,
            "includes" to listOf("**/jacoco/*.xml")
        )
    ).files
    property("sonar.coverage.jacoco.xmlReportPaths", jacocoReportXmlFiles.joinToString(", "))
    property("sonar.java.coveragePlugin", "jacoco")
    property("sonar.junit.reportsPath", "build/reports/tests/testDebugUnitTest")
    property("sonar.android.lint.report", "build/reports/lint-results.xml")
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("codescan", GradleBuild::class){
    tasks = listOf("clean", "test", "jacocoTestReport", "sonarqube")
}