@file:JsExport

package geo

import geo.internal.LatLngAltImpl
import geo.internal.LatLngImpl
import kotlin.js.JsExport
import kotlin.js.JsName

@JsName("latLng")
fun LatLng(lat: Double, lng: Double): LatLng = LatLngImpl(lat, lng)

@JsName("latLngAlt")
fun LatLngAlt(lat: Double, lng: Double, alt: Double): LatLngAlt = LatLngAltImpl(lat, lng, alt)