import geo.countries.GenerateCountriesTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("countries-generator")
    signing
}

repositories {
    publicRepos()
}

val generateCountries by tasks.getting(GenerateCountriesTask::class)

kotlin {
    jvm { library() }
    js(IR) { library() }

    targets.configureEach {
        compilations.all {
            compileKotlinTask.dependsOn(generateCountries)
        }
    }
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(generateCountries.outputDir)
            dependencies {
                api(projects.kashCurrency)
                api(kotlinx.serialization.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(projects.expectCoroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)