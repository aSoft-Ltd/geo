import geo.countries.GenerateCountriesTask

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("countries-generator")
}

description = "A kotlin multiplatform library for offline country support"

val generateCountries by tasks.getting(GenerateCountriesTask::class)

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    if (Targeting.OSX) osxTargets() else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

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
                implementation(projects.kommanderCore)
            }
        }
    }
}