import geo.countries.GenerateCountriesTask
import geo.languages.GenerateLanguagesTask
import org.gradle.kotlin.dsl.registering

val generateCountries by tasks.registering(GenerateCountriesTask::class)
