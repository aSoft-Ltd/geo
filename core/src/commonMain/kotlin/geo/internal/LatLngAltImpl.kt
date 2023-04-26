package geo.internal

import geo.LatLngAlt

internal data class LatLngAltImpl(
    override val lat: Double,
    override val lng: Double,
    override val alt: Double
) : LatLngAlt {
    override fun toString(): String = "($lat, $lng, $alt)"
}