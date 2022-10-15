apply {
    from("$rootDir/base_build.gradle")
}

dependencies {
    "api"(project(":features:core"))
    "api"(project(":services:service_cocktail"))
}