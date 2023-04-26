import expect.expect
import geo.LatLng
import geo.LatLngAlt
import geo.serializers.LatLngAltSerializer
import geo.serializers.LatLngSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class LatLngAltSerializationTest {

    @Test
    fun should_serialize_a_lat_lng() {
        val cord = LatLngAlt(10.01, -10.01, 5.01)
        val json = """{"lat":10.01,"lng":-10.01,"alt":5.01}"""
        expect(Json.encodeToString(LatLngAltSerializer, cord)).toBe(json)
    }

    @Test
    fun should_deserialize_a_lat_lng() {
        val json = """{"lat":5.01,"lng":0.01,"alt":5.05}"""
        val cord = LatLngAlt(5.01, 0.01, 5.05)
        expect(Json.decodeFromString(LatLngAltSerializer, json)).toBe(cord)
    }
}