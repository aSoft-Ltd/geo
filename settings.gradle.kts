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
includeSubs("kommander", "../kommander", "core")
includeSubs("kollections", "../kollections", "interoperable")
includeSubs("liquid", "../liquid", "number")
includeSubs("kash", "../kash", "currency")

// dependencies
includeSubs("geo", ".", "core", "countries")