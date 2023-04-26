package geo.serializers

import geo.LatLngAlt
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element

@Serializer(forClass = LatLngAlt::class)
object LatLngAltSerializer : LatLngStrategy<LatLngAlt>() {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("geo.LatLngAlt") {
        element<Double>(LatLngAlt::lat.name)
        element<Double>(LatLngAlt::lng.name)
        element<Double>(LatLngAlt::alt.name)
    }
}