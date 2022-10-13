apply {
    from("$rootDir/base_build.gradle")
}

dependencies {
    "api"(project(":features:core"))
}