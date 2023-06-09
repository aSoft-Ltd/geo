pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("../versions/gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "geo"

includeBuild("../able")

includeBuild("../kash/currency-generator")
includeBuild("./geo-generator")
includeSubs("functions", "../functions", "core")
includeSubs("kommander", "../kommander", "core", "coroutines")
includeSubs("kollections", "../kollections", "atomic", "interoperable")
includeSubs("liquid", "../liquid", "number")
includeSubs("kash", "../kash", "currency")

includeSubs("kevlar", "../kevlar", "core")
includeSubs("kase", "../kase", "core")
includeSubs("koncurrent-executors", "../koncurrent/executors", "core", "coroutines", "mock")
includeSubs("koncurrent-later", "../koncurrent/later", "core", "coroutines", "test")
includeSubs("keep", "../keep", "api", "file", "mock", "test")
includeSubs("lexi", "../lexi", "api", "console")
includeSubs("lexi-test", "../lexi/test", "android")
includeSubs("krest", "../krest", "core")
includeSubs("cinematic-live", "../cinematic/live", "core", "coroutines", "test", "kollections")
includeSubs("cinematic-scene", "../cinematic/scene", "core")

includeSubs("symphony-collections", "../symphony/collections", "core")
includeSubs("symphony-inputs", "../symphony/inputs", "core", "collections")
includeSubs("symphony-collections-renderers", "../symphony/collections/renderers", "string")

// dependencies
includeSubs("geo", ".", "core", "countries", "symphony")