@file:Suppress("NON_EXPORTABLE_TYPE")

package geo

import kollections.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport


@JsExport
@Serializable
data class GeoLocation(
    val lines: List<String>,
    val country: Country,
    val cords: LatLng? = null,
    val code: String? = null
) {
    val address get() = lines.joinToString(", ")
    val addressMultiline get() = lines.joinToString("\n")
}
