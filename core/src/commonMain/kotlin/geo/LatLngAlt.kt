package geo

import kotlin.js.JsExport

@JsExport
interface LatLngAlt : LatLng {
    val alt: Double
}