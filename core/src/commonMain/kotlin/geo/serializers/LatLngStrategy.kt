package geo.serializers

import geo.LatLng
import geo.LatLngAlt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class LatLngStrategy<L : LatLng> : KSerializer<L> {
    override fun deserialize(decoder: Decoder): L {
        val composite = decoder.beginStructure(LatLngAltSerializer.descriptor)
        var lat: Double? = null
        var lng: Double? = null
        var alt: Double? = null
        while (true) {
            when (val index = composite.decodeElementIndex(LatLngAltSerializer.descriptor)) {
                0 -> lat = composite.decodeDoubleElement(LatLngAltSerializer.descriptor, index)
                1 -> lng = composite.decodeDoubleElement(LatLngAltSerializer.descriptor, index)
                2 -> alt = composite.decodeDoubleElement(LatLngAltSerializer.descriptor, index)
                CompositeDecoder.DECODE_DONE -> break
                else -> throw IllegalStateException("Unknown field at $index")
            }
        }
        composite.endStructure(LatLngAltSerializer.descriptor)
        return if (lat != null && lng != null) {
            (if (alt != null) LatLngAlt(lat, lng, alt) else LatLng(lat, lng)) as L
        } else throw RuntimeException("Failed to decode geo.LatLng")
    }

    override fun serialize(encoder: Encoder, value: L) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeDoubleElement(descriptor, 0, value.lat)
        composite.encodeDoubleElement(descriptor, 1, value.lng)
        if (value is LatLngAlt) composite.encodeDoubleElement(descriptor, 2, value.alt)
        composite.endStructure(descriptor)
    }
}