apply {
    from("$rootDir/base_build.gradle")
}
dependencies {
    "api"(project(":component"))
    "api"(project(":features:feature_util"))
    "api"(project(":features:config"))
}