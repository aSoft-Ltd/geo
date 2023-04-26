package geo

import geo.serializers.LatLngSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable(with = LatLngSerializer::class)
interface LatLng {
    val lat: Double
    val lng: Double
}