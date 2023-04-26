package geo.serializers

import geo.LatLng
import geo.LatLngAlt
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element

@Serializer(forClass = LatLng::class)
object LatLngSerializer : LatLngStrategy<LatLng>() {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("geo.LatLng") {
        element<Double>(LatLng::lat.name)
        element<Double>(LatLng::lng.name)
    }
}